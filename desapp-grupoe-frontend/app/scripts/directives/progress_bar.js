(function () {
  'use strict';

  angular
    .module('advApp')
    .directive('progressBar', progressBar);

  progressBar.$inject = ['$rootScope'];

  function progressBar($rootScope) {
    return function ($scope, element, attrs) {
      $scope.$on("loader_show", function () {
        if (element.hasClass("hidden")) {
          element.removeClass("hidden")
        }
      });
      return $scope.$on("loader_hide", function () {
        if (!element.hasClass("hidden")) {
          element.addClass("hidden")
        }
      });
    }
  }

})();
