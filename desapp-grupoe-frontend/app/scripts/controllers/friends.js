'use strict';

angular.module('advApp')
  .controller('FriendsCtrl', ['$scope', 'API', function ($scope, API) {
    $scope.searchText = '';
    $scope.results = [];
    $scope.search = function (searchText) {
      if (searchText.length < 2) {
        $scope.results = [];
        return;
      }

      console.log(searchText);
      API.resource("/users/byName/" + searchText).query().$promise.then(function (res) {
          $scope.results = res;
        }, function (err) {
          console.log(err);
          $scope.results = [];
          return false;
        }
      )
    };
    $scope.addFriend = function (id) {
      API.resource('/users/' + $scope.user.id + '/addFriend/' + id).update(null,
        function (usr) {
          $scope.user = usr;
        }
      );
    }
    $scope.remove = function (f) {
      if (confirm("¿Desea eliminar a " + f[1] + " de su lista de amigos?")) {
        API.resource('/users/' + $scope.user.id + '/removeFriend/' + f[0]).update(null,
          function (usr) {
            $scope.user = usr;
          }
        );
      }
    };
    API.getCurrentUser().then(function (usr) {
      $scope.user = usr;
    });
  }]);
