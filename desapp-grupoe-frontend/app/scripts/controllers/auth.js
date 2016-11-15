angular
  .module('advApp')
  .controller('AuthCtrl', ['$scope', '$window', '$location', 'socialLoginService', '$rootScope', function ($scope, $window, $location, socialLoginService, $rootScope) {
    function unauthorizedState() {
      return !$rootScope.isUserSignedIn && $location.path() != "/" && $location.path() != "";
    }
    $scope.checkLogin = function () {
      if (unauthorizedState()) {
        $window.location.assign("/");
      }
    };
    $scope.logout = function () {
      socialLoginService.logout();
    };
  }]);
