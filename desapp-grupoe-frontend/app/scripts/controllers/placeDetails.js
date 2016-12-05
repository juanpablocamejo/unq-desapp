(function () {
  'use strict';

  angular
    .module('advApp')
    .controller('placeDetailsCtrl', placeDetailsController);
  placeDetailsController.$inject = ['$window', '$scope', 'API', 'current'];
  function placeDetailsController($window, $scope, API, current) {
    if (current.selectedPlace) {
      $scope.place = current.selectedPlace;
      $scope.is_assistant = is_assistant();
    }
    else {
      $window.location.replace('#/search');
    }
    $scope.backToSearch = function () {
      current.fromDetails = true;
      $window.location.replace('#/search');
    };
    $scope.getCoords = function (outing) {
      if (outing) {
        var lat = outing.address[0];
        var long = outing.address[1];
        return lat + ', ' + long;
      } else return '';
    };

    function is_assistant() {
      return $scope.place.assistants.indexOf(current.user.id) > -1;
    }

    $scope.toggleAssistance = function () {
      var action = $scope.is_assistant ? 'addAssistant' : 'removeAssistant';

      API.resource('/places/' + $scope.place.id + '/' + action + '/' + current.user.id).update()
        .$promise.then(
        function (res) {
          $scope.place.assistants = res.assistants;
          Materialize.toast('Asistencia guardada correctamente.', 2000);
        },
        function (res) {
          console.log('error al ' + ($scope.is_assistant ? 'agregar' : 'eliminar') + ' el asistente');
        }
      );
    };
    $scope.gmaps = {
      url: "https://maps.googleapis.com/maps/api/js?key=AIzaSyCaV2l2uewZ3-Cqtj9Z-LX2OrsU0zv99dI",
      zoom: 15
    };

  }

})();
