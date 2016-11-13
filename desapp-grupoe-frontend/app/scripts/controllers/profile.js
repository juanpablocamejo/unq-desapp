'use strict';

angular.module('advApp')
  .controller('ProfileCtrl', function ($scope, $resource) {
    $scope.user = $resource('http://localhost:8080/api/users/1').get();
    $scope.tags = $resource('http://localhost:8080/api/tags').query();
    $scope.selectTag = function (id) {
      var idx = $scope.user.tags.indexOf(id)
      if ($("#chk" + id).prop("checked")) {
        if (idx) {
          $scope.user.tags.push(id);
        }
      }
      else {
        $scope.user.tags.splice(idx, 1);
      }
      console.log($scope.user.tags);
    }
  });
