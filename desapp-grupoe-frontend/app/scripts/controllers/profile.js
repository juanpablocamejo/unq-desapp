(function () {
  'use strict';

  angular.module('advApp')
    .controller('ProfileCtrl', function ($scope, API) {
      function loadTags(tags) {
        tags.forEach(function (t) {
          t.checked = $scope.user.tags.indexOf(t.id) > -1;
          var cat = t.category;
          $scope.tags.push(t);
          if (cat.id < 5 && $scope.categories.indexOf(cat.name) < 0)
            $scope.categories.push(cat.name);
        });
      }

      $scope.loadUser = function () {
        $scope.tags = [];
        $scope.user = {};
        $scope.categories = [];
        API.getCurrentUser()
          .then(function (u) {
            $scope.user = u;
          })
          .then(function () {
            API.resource('/tags').query().$promise
              .then(loadTags);
          });
      };
      $scope.selectTag = function (id) {
        var idx = $scope.user.tags.indexOf(id);
        if ($("#chk" + id).prop("checked")) {
          if (idx) {
            $scope.user.tags.push(id);
          }
        }
        else {
          $scope.user.tags.splice(idx, 1);
        }
      };
      $scope.loadUser();
      $scope.save = function () {
        $scope.user.$update(
          function () {
            Materialize.toast('Cambios guardados correctamente', 2000);
          }
        );
      };
    });
})();
