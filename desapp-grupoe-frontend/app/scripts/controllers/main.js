'use strict';

angular.module('advApp')
  .controller('MainCtrl', function() {
    // Create the factory that share the Fact

    this.goto = function(strategy, path){setTimeout(function(){window.location.replace(path)},250);};
    this.searchButtons = [{
      'name': 'Gasolera',
      'href': '#/search?strategy=inexpensive',
      'description': 'Encontrá opciones gratuitas o baratas para salir y pasarla bien aún cuando estés corto de plata.',
      'class':'card red'
    }, {
      'name': 'Con Amigos',
      'href': '#/search?strategy=friends',
      'description': '¿Planificando el fin de semana con amigos? hacé click acá para encontrar las mejores opciones.',
      'class':'card purple'
    }, {
      'name': 'Saturday Night Fever',
      'href': '#/search?strategy=saturdaynightfever',
      'description': 'Para los que quieren arrancar temprano, y terminar temprano (pero del día siguiente) ;)',
      'class':'card blue'
    }, {
      'name': 'Media Naranja',
      'href': '#/search?strategy=couples',
      'description': 'Siempre es un buen momento para disfrutar de una salida con tu pareja. Encontrá el lugar ideal.',
      'class':'card orange'
    }, {
      'name': 'Sorprendeme',
      'href': '#/search?strategy=surpriseme',
      'description': 'Es hora de que hagas algo distinto, dejate sorprender con nuestras opciones más recomendadas.',
      'class':'green '
    }];
  });
