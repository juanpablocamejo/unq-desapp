'use strict';

angular.module('advApp')
  .controller('FriendsCtrl', function ($scope, API) {
    $scope.loadUser = function () {
      $scope.tags = [];
      $scope.user = {};
      $scope.categories = [];
      API.resource('/users/:id').get({id: 1}).$promise
        .then(function (u) {
          $scope.user = u;
          console.log(u);
        })
        .then(function () {
          API.resource('/tags').query().$promise
            .then(loadTags);
        });
    };

    $scope.loadUser();
  });
