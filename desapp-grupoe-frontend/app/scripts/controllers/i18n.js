(function () {

  'use strict';

  angular.module('advApp')
    .controller('LocaleCtrl', i18nController);

  i18nController.$inject = ['$scope', '$translate', 'tmhDynamicLocale'];

  function i18nController($scope, $translate, tmhDynamicLocale) {
    $scope.changeLanguage = function (langKey) {
      $translate.use(langKey);
      tmhDynamicLocale.set(langKey);
    };
  }

})();
