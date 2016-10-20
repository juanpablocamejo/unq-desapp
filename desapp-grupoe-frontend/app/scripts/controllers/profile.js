'use strict';

angular.module('advApp')
  .controller('ProfileCtrl', function($scope,$resource) {
    $scope.user = $resource('http://localhost:8080/api/users/1').get();
  });
