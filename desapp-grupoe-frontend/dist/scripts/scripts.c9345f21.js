"use strict";
angular.module("advApp", ["ngAnimate", "ngCookies", "ngResource", "ngRoute", "ngSanitize", "ui.materialize", "pascalprecht.translate", "tmh.dynamicLocale", "angular.filter"]).config(["$routeProvider", function (a) {
  a.when("/", {
    templateUrl: "views/main.html",
    controller: "MainCtrl",
    controllerAs: "main"
  }).when("/search", {
    templateUrl: "views/search.html",
    controller: "SearchCtrl",
    controllerAs: "search"
  }).when("/profile", {
    templateUrl: "views/profile.html",
    controller: "ProfileCtrl",
    controllerAs: "profile"
  }).otherwise({redirectTo: "/"})
}]).config(["$translateProvider", "tmhDynamicLocaleProvider", function (a, b) {
  a.useStaticFilesLoader({
    prefix: "/i18n/",
    suffix: ".json"
  }).useSanitizeValueStrategy("sanitize").preferredLanguage("es"), b.localeLocationPattern("/i18n/angular-locale_{{locale}}.js")
}]), $(function () {
  $(".button-collapse").sideNav({menuWidth: 300, edge: "right", closeOnClick: !0})
}), angular.module("advApp").controller("MainCtrl", function () {
  var a = this;
  a.a_date = new Date, a["goto"] = function (a, b) {
    setTimeout(function () {
      window.location.replace(b)
    }, 150)
  }, a.searchButtons = [{
    name: "search_strategies.inexpensive.title",
    href: "#/search?strategy=inexpensive",
    description: "search_strategies.inexpensive.description",
    "class": "card red"
  }, {
    name: "search_strategies.friends.title",
    href: "#/search?strategy=friends",
    description: "search_strategies.friends.description",
    "class": "purple"
  }, {
    name: "search_strategies.sat_night_fever.title",
    href: "#/search?strategy=saturdaynightfever",
    description: "search_strategies.sat_night_fever.description",
    "class": "blue"
  }, {
    name: "search_strategies.couples.title",
    href: "#/search?strategy=couples",
    description: "search_strategies.sat_night_fever.description",
    "class": "orange"
  }, {
    name: "search_strategies.surprise_me.title",
    href: "#/search?strategy=surpriseme",
    description: "search_strategies.surprise_me.description",
    "class": "green "
  }]
}), angular.module("advApp").controller("ProfileCtrl", ["$scope", "$resource", function (a, b) {
  a.user = b("http://localhost:8080/api/users/1").get(), a.tags = b("http://localhost:8080/api/tags").query(), a.selectTag = function (b) {
    var c = a.user.tags.indexOf(b);
    $("#chk" + b).prop("checked") ? c && a.user.tags.push(b) : a.user.tags.splice(c, 1), console.log(a.user.tags)
  }
}]), angular.module("advApp").controller("SearchCtrl", function () {
}), angular.module("advApp").controller("LocaleCtrl", ["$translate", "$scope", "tmhDynamicLocale", function (a, b, c) {
  b.changeLanguage = function (b) {
    a.use(b), c.set(b)
  }
}]), angular.module("advApp").filter("money", ["$translate", function (a) {
  return function (b) {
    var c = "en" === a.use();
    return c ? b / 15 : b
  }
}]), angular.module("advApp").run(["$templateCache", function (a) {
  a.put("views/login.html", '<!DOCTYPE html> <html lang="en"> <head> <meta charset="UTF-8"> <title></title> </head> <body> </body> </html>'), a.put("views/main.html", '<div class="row"> <div class="col s12 m12 l6 center-align" ng-repeat="btn in main.searchButtons"> <div class="main-btn waves-effect waves-light card-panel hoverable opacity-80 txt-shadow {{btn.class}}" ng-click="main.goto(btn.name, btn.href)"> <span class="white-text"> <h5 translate>{{btn.name}}</h5> <p translate>{{btn.description}}</p> </span> </div> </div> </div>'), a.put("views/profile.html", '<div class="card-panel"> <h5></h5> <div class="row"> <div input-field class="col s12 m6 l5"> <label>Nombre</label> <input type="text" ng-model="user.name"> </div> <div input-field class="col s12 m6 l5"> <label>Apellido</label> <input type="text" ng-model="user.surname"> </div> <div input-field class="col s12 m6 l5"> <label>Email</label> <input type="text" user.email> </div> </div> <div class="row"> <div input-field class="col s12 m6 l5"> <label>Limite para salidas baratas</label> <input type="number" ng-model="user.inexpensiveOutingLimit"> </div> <div class="col s12 m6 l5" ng-repeat="(category ,cat_tags) in tags | groupBy:\'category.name\'"> <h6>{{category}}</h6> <div ng-repeat="t in cat_tags"> <input type="checkbox" id="chk{{t.id}}" ng-click="selectTag(t.id)"> <label for="chk{{t.id}}">{{t.name}}</label> </div> </div> </div> </div>'), a.put("views/search.html", '<p><h3>Sección de búsqueda</h3></p> <p ng-repeat="u in search.users">{{u.name}}</p>')
}]);
