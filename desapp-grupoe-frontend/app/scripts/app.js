'use strict';

/**
 * @ngdoc overview
 * @name advApp
 * @description
 * # advApp
 *
 * Main module of the application.
 */
angular
  .module('advApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ui.materialize',
    'pascalprecht.translate',
    'tmh.dynamicLocale',
    'angular.filter',
    'adv.gauth',
    'angularUtils.directives.dirPagination',
    'psResponsive',
    'ngMap'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/#', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/search', {
        templateUrl: 'views/search.html',
        controller: 'SearchCtrl',
        controllerAs: 'search'
      })
      .when('/profile', {
        templateUrl: 'views/profile.html',
        controller: 'ProfileCtrl',
        controllerAs: 'profile'
      })
      .when('/friends', {
        templateUrl: 'views/friends.html',
        controller: 'FriendsCtrl',
        controllerAs: 'friends'
      })
      .when('/event_details', {
        templateUrl: 'views/eventDetails.html',
        controller: 'eventDetailsCtrl',
        controllerAs: 'friends'
      })
      .when('/place_details', {
        templateUrl: 'views/placeDetails.html',
        controller: 'placeDetailsCtrl',
        controllerAs: 'friends'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  .config(function ($translateProvider, tmhDynamicLocaleProvider) {
    $translateProvider
      .useStaticFilesLoader({
        prefix: '/i18n/',
        suffix: '.json'
      })
      .useSanitizeValueStrategy('sanitize')
      .preferredLanguage('es');
    tmhDynamicLocaleProvider.localeLocationPattern('/i18n/angular-locale_{{locale}}.js');
  })
  .config(function (gauthProvider) {
    gauthProvider.setGoogleKey("1012008431025-usfsjk7vqtbe1qgksj60tuh7g32ntv31.apps.googleusercontent.com");
  })
  .config(function ($httpProvider) {
    $httpProvider.interceptors.push('httpinterceptor');
  }).run(function ($rootScope) {
  $rootScope.currentStrategy = 'surprise_me';
});

/*global $*/
$(function () {
  $('.button-collapse').sideNav({
    menuWidth: 240, // Default is 240
    edge: 'right', // Choose the horizontal origin
    closeOnClick: true // Closes side-nav on <a> clicks, useful for Angular/Meteor
  });
});

