'use strict';

angular.module('advApp')
  .controller('MainCtrl', ['$rootScope', '$window', '$timeout', '$scope', function ($rootScope, $window, $timeout, $scope) {

    var vm = this;
    vm.a_date = new Date();
    vm.goto = function (strategy, path) {
      $rootScope.currentStrategy = strategy.split('.')[1];
      $timeout(function () {
        $window.location.replace(path);
      }, 150);
    };
    vm.searchButtons = [{
      'name': 'search_strategies.inexpensive.title',
      'href': '#/search',
      'description': 'search_strategies.inexpensive.description',
      'color': 'red'
    }, {
      'name': 'search_strategies.friends.title',
      'href': '#/search',
      'description': 'search_strategies.friends.description',
      'color': 'purple'
    }, {
      'name': 'search_strategies.sat_night_fever.title',
      'href': '#/search',
      'description': 'search_strategies.sat_night_fever.description',
      'color': 'blue'
    }, {
      'name': 'search_strategies.couples.title',
      'href': '#/search',
      'description': 'search_strategies.couples.description',
      'color': 'orange'
    }, {
      'name': 'search_strategies.surprise_me.title',
      'href': '#/search',
      'description': 'search_strategies.surprise_me.description',
      'color': 'green '
    }];
  }]);
