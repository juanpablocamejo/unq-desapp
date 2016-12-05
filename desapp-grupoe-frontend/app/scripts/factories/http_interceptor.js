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

        // Show loader
        $rootScope.$broadcast("loader_show");
        return config || $q.when(config)

      },
      requestError: function (rejection) {
        if ((--numLoadings) === 0) {
          // Hide loader
          hide();//$rootScope.$broadcast("loader_hide");
        }
        return rejection || $q.when(rejection);
      },
      response: function (response) {

        if ((--numLoadings) === 0) {
          // Hide loader
          hide();//$rootScope.$broadcast("loader_hide");
        }

        return response || $q.when(response);

      },
      responseError: function (response) {

        if ((--numLoadings) === 0) {
          // Hide loader
          hide();//$rootScope.$broadcast("loader_hide");
        }

        return $q.reject(response);
      }
    };
  }
})();


