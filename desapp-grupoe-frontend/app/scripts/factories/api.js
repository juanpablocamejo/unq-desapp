(function () {
  'use strict';

  function factory($resource, $rootScope, $location, current, $q) {
    factory.$inject = ['$resource', '$rootScope', '$location', 'current', '$q'];

    var service = {
      resource: getResource,
      getOrCreateUser: getOrCreate,
      getCurrentUser: getCurrUser
    };

    return service;

    function getCurrUser() {
      return getResource('/users/:id').get({id: current.user.id}).$promise;
    }

    function getResource(path) {
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

    function getOrCreate(googleUser) {
      var def = $q.defer();

      getResource("/users/byEmail/" + googleUser.email)
        .get()
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

  angular
    .module('advApp')
    .factory('API', factory);
})();
