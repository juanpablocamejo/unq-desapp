(function () {
  'use strict';
  angular
    .module('advApp')
    .factory('httpinterceptor', httpinterceptor);
  function httpinterceptor($q, $rootScope, $timeout) {
    httpinterceptor.$inject = ['$q', '$rootScope', '$timeout'];
    var numLoadings = 0;

    function hide() {
      $timeout(function () {
        $rootScope.$broadcast("loader_hide");
      }, 500);
    }

    return {
      request: function (config) {
        numLoadings++;

        $rootScope.$broadcast("loader_show");
        return config || $q.when(config)

      },
      requestError: function (rejection) {
        if ((--numLoadings) === 0) {
          hide();
        }
        return rejection || $q.when(rejection);
      },
      response: function (response) {

        if ((--numLoadings) === 0) {
          hide();
        }

        return response || $q.when(response);

      },
      responseError: function (response) {

        if ((--numLoadings) === 0) {
          hide();
          if (response.status == 400) {
            $rootScope.error_message = response.data.message;
            $timeout(function () {
              $rootScope.error_message = null;
            }, 2000);
          }
        }

        return $q.reject(response);
      }
    };
  }
})();


