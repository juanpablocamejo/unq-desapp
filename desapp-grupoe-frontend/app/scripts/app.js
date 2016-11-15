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
    'socialLogin',
    'datatables'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/main', {
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
  .config(function (socialProvider) {
    socialProvider.setGoogleKey("1012008431025-usfsjk7vqtbe1qgksj60tuh7g32ntv31.apps.googleusercontent.com");
  })
  .run(function ($rootScope, $location, $window, API) {
    $rootScope.$on('event:social-sign-in-success', function (event, user) {
      $rootScope.isUserSignedIn = true;
      $rootScope.googleUser = user;
      API.getOrCreateUser();
      $window.location.assign("/#main");
    });

    $rootScope.$on('event:social-sign-out-success', function () {
      $rootScope.isUserSignedIn = false;
      $rootScope.googleUser = null;
      $window.location.assign("/");
    });

    $rootScope.$on('event:social-sign-state-changed', function (event, state) {
      $rootScope.isUserSignedIn = state;
      if (!$rootScope.isUserSignedIn && $location.path() != '/' && $location.path() != '') $window.location.assign("/");
    });
  });

/*global $*/
$(function () {
  $('.button-collapse').sideNav({
    menuWidth: 300, // Default is 240
    edge: 'right', // Choose the horizontal origin
    closeOnClick: true // Closes side-nav on <a> clicks, useful for Angular/Meteor
  });
});
