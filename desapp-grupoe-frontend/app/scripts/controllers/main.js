'use strict';

angular.module('advApp')
  .controller('MainCtrl', function () {
    var vm = this;
    vm.a_date = new Date();
    vm.goto = function (strategy, path) {
      setTimeout(function () {
        window.location.replace(path);
      }, 150);
    };
    vm.searchButtons = [{
      'name': 'search_strategies.inexpensive.title',
      'href': '#/search?strategy=inexpensive',
      'description': 'search_strategies.inexpensive.description',
      'color': 'red'
    }, {
      'name': 'search_strategies.friends.title',
      'href': '#/search?strategy=friends',
      'description': 'search_strategies.friends.description',
      'color': 'purple'
    }, {
      'name': 'search_strategies.sat_night_fever.title',
      'href': '#/search?strategy=saturday_night_fever',
      'description': 'search_strategies.sat_night_fever.description',
      'color': 'blue'
    }, {
      'name': 'search_strategies.couples.title',
      'href': '#/search?strategy=couples',
      'description': 'search_strategies.couples.description',
      'color': 'orange'
    }, {
      'name': 'search_strategies.surprise_me.title',
      'href': '#/search?strategy=surprise_me',
      'description': 'search_strategies.surprise_me.description',
      'color': 'green '
    }];
  });
