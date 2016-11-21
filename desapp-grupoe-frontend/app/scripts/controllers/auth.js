(function () {
  'use strict';

  angular
    .module('advApp')
    .value('current', {user: null})
    .controller('AuthCtrl', authController);

  authController.$inject = ['$scope', 'gauth', 'current', 'API', '$route'];

  function authController($scope, gauth, current, API, $route) {
    $scope.signedIn = {value: false};
    gauth.addListener(function (res) {
      API.getOrCreateUser(gauth.getCurrentState().user)
        .then(function (usr) {
          current.user = usr;
          $scope.signedIn.value = res;
        });
    });

    $scope.logout = function () {
      gauth.logout().then(
        function () {
          $scope.signedIn.value = false;
          current.user = null;
        }
      );
    };

    $scope.login = function () {
      gauth.login().then(
        function (user) {
          $scope.signedIn.value = true;
          API.getOrCreateUser(user).then(
            function (usr) {
              current.user = usr;
              $scope.signedIn.value = true;
              $route.reload();
            }
          );

        }
      );
    };
  }
})();



