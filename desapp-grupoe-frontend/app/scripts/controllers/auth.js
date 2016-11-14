angular
  .module('advApp')
  .controller('AuthCtrl', ['$scope', '$window', '$location', 'socialLoginService', '$rootScope', function ($scope, $window, $location, socialLoginService, $rootScope) {
    $scope.checkLogin = function () {
      if (!$rootScope.isUserSignedIn && $location.path() != '/' && $location.path() != '') {
        $window.location.assign("/");
      }
    };
    $scope.logout = function () {
      socialLoginService.logout();
    };
    if (!$scope.isUserSignedIn && $location.path() != "/" && $location.path() != "") $window.location.assign("/");

  }]);
