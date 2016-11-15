angular
  .module('advApp')
  .controller('LoginCtrl', ['$scope', '$window', '$rootScope', function ($scope, $window, $rootScope) {
    $scope.checkLogin = function () {
      if ($rootScope.isUserSignedIn) {
        $window.location.assign("/#/main");
      }
    };
  }]);
