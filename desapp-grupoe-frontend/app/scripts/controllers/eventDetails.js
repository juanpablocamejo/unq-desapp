(function () {
  'use strict';

  angular
    .module('advApp')
    .controller('eventDetailsCtrl', eventDetailsController);
  eventDetailsController.$inject = ['$window', '$scope', 'API', 'current'];
  function eventDetailsController($window, $scope, API, current) {
    if (current.selectedEvent) {
      $scope.event = current.selectedEvent;
    }
    else {
      current.fromDetails = true;
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
      return $scope.event.assistants.indexOf(current.user.id) > -1;
    }

    $scope.toggleAssistance = function () {
      var action = $scope.is_assistant ? 'addAssistant' : 'removeAssistant';

      API.resource('/places/' + $scope.event.id + '/' + action + '/' + current.user.id).update()
        .$promise.then(
        function (res) {
          $scope.event.assistants = res.assistants;
          Materialize.toast('Asistencia guardada correctamente.', 2000);
        },
        function (res) {
          console.log('error al ' + ($scope.is_assistant ? 'agregar' : 'eliminar') + ' el asistente', res);
        }
      );
    };
    $scope.gmaps = {
      url: "https://maps.googleapis.com/maps/api/js?key=AIzaSyCaV2l2uewZ3-Cqtj9Z-LX2OrsU0zv99dI",
      zoom: 15
    };
  }

})();
