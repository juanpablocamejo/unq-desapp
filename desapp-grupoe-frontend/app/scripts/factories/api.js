(function () {
  'use strict';

  function factory($resource, $rootScope, $location) {
    factory.$inject = ['$resource', '$rootScope', '$location'];

    function getResource(path) {
      if ($rootScope.isUserSignedIn) {
        var host = $location.host();
        var apiPath = (host === 'localhost') ? ':8080/api' : '/api';
        return $resource('http://' + host + apiPath + path, null, {
            'get': {method: 'GET'},
            'save': {method: 'POST'},
            'update': {method: 'PUT'},
            'query': {method: 'GET', isArray: true},
            'remove': {method: 'DELETE'},
            'delete': {method: 'DELETE'}
          }
        );
      }
    }

    function getOrCreate() {
      getResource("/users/byEmail/:email", {email: $rootScope.currentUser.email}).get().$promise.then(
        function (res) {
          $rootScope.user = res;
        },
        function () {
          //TODO: create new user
        }
      )
    }

    var service = {
      resource: getResource,
      getOrCreateUser: getOrCreate
    };

    return service;
  }

  angular
    .module('advApp')
    .factory('API', factory);
})();
