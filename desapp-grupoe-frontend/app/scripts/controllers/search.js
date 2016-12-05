(function () {
  'use strict';

  angular.module('advApp')
    .controller('SearchCtrl', searchController);
  searchController.$inject = ['$scope', '$rootScope', '$window', '$location', '$timeout', 'psResponsive', 'API', 'current'];
  function searchController($scope, $rootScope, $window, $location, $timeout, psResponsive, API, current) {
    if (!current.fromDetails) {
      current.places = [];
      current.events = [];
    }
    current.fromDetails = false;
    $scope.setEventsPageSize = function (n, elem) {
      $scope.eventsPageSize = n;
    };
    $scope.setPlacesPageSize = function (n, elem) {
      $scope.placesPageSize = n;
    };
    $scope.isEventsPageSize = function (n) {
      return n == $scope.eventsPageSize;
    };
    $scope.isPlacesPageSize = function (n) {
      return n == $scope.placesPageSize;
    };
    $scope.eventsPageSize = 5;
    $scope.placesPageSize = 5;
    $scope.responsive = psResponsive;
    $scope.strategy = 'search_strategies.' + $rootScope.currentStrategy;
    $scope.cnt = 1;
    $scope.events = current.events ? current.events : [];
    $scope.places = current.places ? current.places : [];
    $scope.viewPlace = function (p) {
      $timeout(function () {
        current.selectedPlace = p;
        $window.location.replace('#/place_details');
      }, 150);
    };
    $scope.viewEvent = function (e) {
      $timeout(function () {
        current.selectedEvent = e;
        $window.location.replace('#/event_details');
      }, 150);
    };
    $scope.searchAll = function () {
      var qs = "?strategy=" + $rootScope.currentStrategy + "&user=" + $scope.user.id + "&assistants=" + $scope.cnt;
      API.resource('/events/search' + qs).query().$promise.then(
        function (events) {
          $scope.events = events;
          current.events = events;
        }, function () {
          $scope.events = [];
          current.events = [];
        });
      API.resource('/places/search' + qs).query().$promise.then(
        function (places) {
          $scope.places = places;
          current.places = places;
        }, function () {
          current.places = [];
          $scope.places = [];
        });
    };
    API.getCurrentUser().then(
      function (usr) {
        $scope.user = usr;
      }
    );


  }
})();
