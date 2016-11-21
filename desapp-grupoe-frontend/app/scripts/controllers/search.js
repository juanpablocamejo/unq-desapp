'use strict';

angular.module('advApp')
  .controller('SearchCtrl', ['$scope', '$rootScope', 'API', function ($scope, $rootScope, API) {
    $scope.strategy = 'search_strategies.' + $rootScope.currentStrategy;
    $scope.cnt = 1;
    $scope.events = [];
    $scope.places = [];
    $scope.log = function () {
      console.log($scope.cnt);
    }

    $scope.searchAll = function () {
      var qs = "?strategy=" + $rootScope.currentStrategy + "&user=" + $scope.user.id + "&assistants=" + $scope.cnt;
      API.resource('/events/search' + qs).query().$promise.then(
        function (events) {
          $scope.events = events;
        }, function () {
          $scope.events = [];
        });
      API.resource('/places/search' + qs).query().$promise.then(
        function (places) {
          $scope.places = places;
        }, function () {
          $scope.places = [];
        });
    };
    API.getCurrentUser().then(
      function (usr) {
        $scope.user = usr;
      }
    );
  }]);
