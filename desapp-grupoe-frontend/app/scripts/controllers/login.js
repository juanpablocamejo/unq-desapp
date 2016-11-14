angular
  .module('advApp')
  .controller('LoginCtrl', ['$scope', '$window', '$location', 'socialLoginService', '$rootScope', function ($scope, $window, $location, socialLoginService, $rootScope) {
    $scope.checkLogin = function () {
      if ($rootScope.isUserSignedIn) {
        $window.location.assign("/#/main");
      }
    };
  }]);
