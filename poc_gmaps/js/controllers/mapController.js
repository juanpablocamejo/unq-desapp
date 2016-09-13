(function() {
    'use strict';
    angular
        .module('gmaps', ['ngMap'])
        .controller('MapController', function(NgMap) {
            var vm = this;
            NgMap.getMap().then(function(map) {
                vm.map = map;
            });
            vm.urlGmaps = "https://maps.googleapis.com/maps/api/js?key=AIzaSyCaV2l2uewZ3-Cqtj9Z-LX2OrsU0zv99dI";
            vm.config = {
                zoom: 13,
                center: "buenos aires"
            };
            vm.markers = [
                {address: '[-34.60375,-58.3807633]', color: 'red', text: 'Las Cuartetas', animation:'Animation.BOUNCE'},
                {address: '[-34.6033243,-58.377882]', color: 'green', text: 'El Palacio de la pizza'},
                {address: '[-34.6040792,-58.3880116]', color: 'yellow', text: 'Güerrin'}
                ];
        });

})();