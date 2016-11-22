(function () {
  'use strict';

  angular
    .module('advApp')
    .controller('FriendsCtrl', friendsController);
  friendsController.$inject = ['$scope', 'API', 'current', '$filter'];
  function friendsController($scope, API, current, $filter) {
    $scope.user = current.user;
    $scope.searchText = '';
    $scope.results = [];
    $scope.search = function (searchText) {
      if (searchText.length < 2) {
        $scope.results = [];
        return;
      }

      API.resource("/users/byName/" + searchText).query().$promise.then(function (res) {
        $scope.results = _.filter(res, function (r) {
          return _.every($scope.user.friends,
            function (f) {
              return f[0] != r[0] && $scope.user.id != r[0];
            }
          );
        });

        }, function (err) {
          console.log(err);
          $scope.results = [];
          return false;
        }
      );
    };
    $scope.addFriend = function (f) {
      API.resource('/users/' + $scope.user.id + '/addFriend/' + f[0]).update(null,
        function (usr) {
          $scope.user = usr;
          Materialize.toast($filter('translate')('add_friend_ok'), 2000);
        }
      );
    };
    $scope.remove = function (f) {
      if (confirm("¿Desea eliminar a " + f[1] + " de su lista de amigos?")) {
        API.resource('/users/' + $scope.user.id + '/removeFriend/' + f[0]).update(null,
          function (usr) {
            $scope.user = usr;

            Materialize.toast($filter('translate')('remove_friend_ok'), 2000);
          }
        );
      }
    };
  }
})();
