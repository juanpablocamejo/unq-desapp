(function () {
  'use strict';

  function factory($resource, $rootScope, $location) {
    factory.$inject = ['$resource', '$rootScope', '$location'];

    function getCurrUser() {
      if ($rootScope.isUserSignedIn) {
        return getResource('/users/:id').get({id: $rootScope.currentUserId}).$promise;
      }
    }
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
      getResource("/users/byEmail/:email").get({email: $rootScope.googleUser.email}).$promise.then(
        function (res) {
          console.log(res);
          getResource("/users/:id").get({id: res.id}, function (usr) {
            $rootScope.currentUserId = usr.id;
          });
        },
        function (res) {
          console.log(res);
          var user = {
            "address": ["0.0", "0.0", "Buenos Aires"],
            "name": $rootScope.googleUser.name,
            "inexpensiveOutingLimit": 100,
            "surname": $rootScope.googleUser.surname,
            "tags": [],
            "friends": [],
            "image": $rootScope.googleUser.image,
            "email": $rootScope.googleUser.email
          };
          getResource("/users").save(user, function (res) {
            $rootScope.currentUserId = res.id;
          });
        }
      )
    }

    var service = {
      resource: getResource,
      getOrCreateUser: getOrCreate,
      getCurrentUser: getCurrUser
    };

    return service;
  }

  angular
    .module('advApp')
    .factory('API', factory);
})();
