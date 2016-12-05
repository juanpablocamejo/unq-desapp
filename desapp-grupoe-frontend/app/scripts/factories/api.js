(function () {
  'use strict';
  angular
    .module('advApp')
    .factory('API', APIfactory);
  function APIfactory($resource, $rootScope, $location, current, $q, gauth) {
    APIfactory.$inject = ['$resource', '$rootScope', '$location', 'current', '$q', 'gauth'];

    var service = {
      resource: getResource,
      getOrCreateUser: getOrCreate,
      getCurrentUser: getCurrUser
    };

    return service;

    function getCurrUser() {
      var _email = gauth.getCurrentState().user.email;
      return getResource('/users/:filter/:email').get({filter: 'byEmail', email: _email}).$promise;
    }

    function getResource(path) {
      var host = $location.host();
      var isLocal = host === 'localhost';
      var apiPath = isLocal ? ':8080/api' : '/api';
      return $resource((isLocal ? 'http://' : 'https://') + host + apiPath + path, null, {
          'get': {method: 'GET'},
          'save': {method: 'POST'},
          'update': {method: 'PUT'},
          'query': {method: 'GET', isArray: true},
          'remove': {method: 'DELETE'},
          'delete': {method: 'DELETE'}
        }
      );
    }

    function getOrCreate(googleUser) {
      var def = $q.defer();

      getResource("/users/:filter/:email/")
        .get({filter: 'byEmail', email: googleUser.email})
        .$promise
        .then(getSuccess, getFail);

      return def.promise;

      function getSuccess(res) {
        def.resolve(res);
      };
      function getFail(res) {
        var user = {
          "address": ["0.0", "0.0", "Buenos Aires"],
          "name": googleUser.name,
          "inexpensiveOutingLimit": 100,
          "surname": googleUser.surname,
          "tags": [],
          "friends": [],
          "image": googleUser.image,
          "email": googleUser.email
        };
        getResource("/users")
          .save(user)
          .$promise
          .then(
            function (res) {
              def.resolve(res);
            }
          );
      };
    }

  }
})();
