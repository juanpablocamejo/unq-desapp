'use strict';

/**
 * @ngdoc filter
 * @name advApp.filter:toDollar
 * @function
 * @description
 * # toDollar
 * Filter in the advApp.
 */
angular.module('advApp')
  .filter('money', function ($translate) {
    return function (input) {
      var use_dollar = $translate.use() === 'en';
      return use_dollar ? (input / 15) : input;
    };
  });
