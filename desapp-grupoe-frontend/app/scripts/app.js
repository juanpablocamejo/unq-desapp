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
    'datatables'
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
  });

/*global $*/
$(function () {
  $('.button-collapse').sideNav({
    menuWidth: 300, // Default is 240
    edge: 'right', // Choose the horizontal origin
    closeOnClick: true // Closes side-nav on <a> clicks, useful for Angular/Meteor
  });
});
