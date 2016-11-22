/**
 * Created by K-ME on 19/11/2016.
 */
(function () {
  'use strict';

  angular
    .module('adv.gauth', []);
})();


//provider
(function () {
  'use strict';

  angular
    .module('adv.gauth')
    .provider('gauth', gauthProvider);

  function gauthProvider() {
    var interval;
    var gauth_auth = null;
    var listeners = [];
    var currentState = {user: null, signedIn: false};
    var gauthParams = {
      clientid: "",
      scope: 'email',
      cookie_policy: 'single_host_origin'
    };

    this.setGoogleKey = function (value) {
      gauthParams.clientid = value;
    };

    this.$get = gauthFactory;
    gauthFactory.$inject = ['$q', '$http'];

    //service
    function gauthFactory($q, $http) {
      var service = {
        getGoogleKey: _getGoogleKey,
        getCurrentState: _getCurrentState,
        reloadState: _reloadState,
        addListener: _addListener,
        getListeners: _getListeners,
        login: _login,
        logout: _logout
      };

      return service;


      function _reloadState() {
        saveUserData(authInstance().currentUser.get());
      }

      function _getListeners() {
        return listeners;
      }

      function _getCurrentState() {
        return currentState;
      }

      function _getGoogleKey() {
        return gauthParams.clientid;
      }

      function _login() {
        var def = $q.defer();
        authInstance()
          .signIn()
          .then(
            function (res) {
              loginSuccess(res);
              def.resolve(currentState.user);
            },
            function (res) {
              loginFail(res);
              def.reject(res);
            }
          );
        return def.promise;
      }

      function _logout() {
        var def = $q.defer();
        //authInstance()
        var gElement = document.getElementById("gSignout");
        if (typeof(gElement) != 'undefined' && gElement != null) {
          gElement.remove();
        }
        var d = document;
        var ref = d.getElementsByTagName('script')[0];
        var gSignout = d.createElement('script');
        gSignout.src = "https://accounts.google.com/Logout";
        gSignout.type = "text/javascript";
        gSignout.id = "gSignout";
        ref.parentNode.insertBefore(gSignout, ref);
        logoutSuccess();
        def.resolve();
        // .then(
        //   function (res) {
        //     logoutSuccess(res);
        //     def.resolve(res);
        //   },
        //   function (res) {
        //     logoutFail(res);
        //     def.reject(res);
        //   }
        // );

        return def.promise;
      }

      function _addListener(listener) {
        listeners.push(listener);
      }

      function loginSuccess(googleUser) {
        saveUserData(googleUser);
      }

      function loginFail(err) {
        clearCurrentState();
      }

      function logoutSuccess() {
        clearCurrentState();
      }

      function logoutFail(err) {
        console.log({'logout fail': err});
      }

      function clearCurrentState() {
        currentState.user = null;
        currentState.signedIn = false;
      }

      function saveUserData(googleUser) {
        var profile = googleUser.getBasicProfile();
        var idToken = googleUser.getAuthResponse().id_token;
        currentState.user = {
          token: idToken,
          name: profile.getGivenName(),
          surname: profile.getFamilyName(),
          email: profile.getEmail(),
          uid: profile.getId(),
          imageUrl: profile.getImageUrl()
        };
      }

      function authInstance() {
        if (gauth_auth == null)
          gauth_auth = gapi.auth2.getAuthInstance();
        return gauth_auth;
      }
    }
  }
})();


//directive
(function () {
  'use strict';

  angular
    .module('adv.gauth')
    .directive('ngGauth', gauth);

  gauth.$inject = ['$window', 'gauth', '$timeout'];

  function gauth($window, gauth, $timeout) {
    var directive = {
      link: link,
      restrict: 'EA'
    };
    return directive;

    function link(scope, element, attrs) {
      var d = $window.document, gJs, ref = d.getElementsByTagName('script')[0];
      gJs = d.createElement('script');
      gJs.async = true;
      gJs.src = "//apis.google.com/js/platform.js"

      gJs.onload = function () {
        var params = {
          client_id: gauth.getGoogleKey(),
          scope: 'email',
          cookie_policy: 'single_host_origin'
        };
        gapi.load('auth2', function () {
          gapi.auth2.init(params);
          gapi.auth2.getAuthInstance().isSignedIn.listen(
            function (res) {
              var ls = gauth.getListeners();
              for (var i in ls) {
                gauth.reloadState();
                $timeout(ls[i], 0, true, res);
              }
            });
        });
      };

      ref.parentNode.insertBefore(gJs, ref);

    }
  }

})();
