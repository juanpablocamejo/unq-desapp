"use strict";angular.module("advApp",["ngAnimate","ngCookies","ngResource","ngRoute","ngSanitize","ui.materialize","pascalprecht.translate","tmh.dynamicLocale","angular.filter","socialLogin"]).config(["$routeProvider",function(a){a.when("/",{templateUrl:"views/main.html",controller:"MainCtrl",controllerAs:"main"}).when("/main",{templateUrl:"views/main.html",controller:"MainCtrl",controllerAs:"main"}).when("/search",{templateUrl:"views/search.html",controller:"SearchCtrl",controllerAs:"search"}).when("/profile",{templateUrl:"views/profile.html",controller:"ProfileCtrl",controllerAs:"profile"}).when("/friends",{templateUrl:"views/friends.html",controller:"ProfileCtrl",controllerAs:"profile"}).otherwise({redirectTo:"/"})}]).config(["$translateProvider","tmhDynamicLocaleProvider",function(a,b){a.useStaticFilesLoader({prefix:"/i18n/",suffix:".json"}).useSanitizeValueStrategy("sanitize").preferredLanguage("es"),b.localeLocationPattern("/i18n/angular-locale_{{locale}}.js")}]).config(["socialProvider",function(a){a.setGoogleKey("1012008431025-usfsjk7vqtbe1qgksj60tuh7g32ntv31.apps.googleusercontent.com")}]).run(["$rootScope","$location","$window","API",function(a,b,c,d){a.$on("event:social-sign-in-success",function(b,e){a.isUserSignedIn=!0,a.currentUser=e,d.getOrCreateUser(),c.location.assign("/#main")}),a.$on("event:social-sign-out-success",function(){a.isUserSignedIn=!1,a.currentUser=null,c.location.assign("/")}),a.$on("event:social-sign-state-changed",function(d,e){a.isUserSignedIn=e,a.isUserSignedIn||"/"==b.path()||""==b.path()||c.location.assign("/")})}]),$(function(){$(".button-collapse").sideNav({menuWidth:300,edge:"right",closeOnClick:!0})}),angular.module("advApp").controller("AuthCtrl",["$scope","$window","$location","socialLoginService","$rootScope",function(a,b,c,d,e){a.checkLogin=function(){e.isUserSignedIn||"/"==c.path()||""==c.path()||b.location.assign("/")},a.logout=function(){d.logout()},a.isUserSignedIn||"/"==c.path()||""==c.path()||b.location.assign("/")}]),angular.module("advApp").controller("LoginCtrl",["$scope","$window","$location","socialLoginService","$rootScope",function(a,b,c,d,e){a.checkLogin=function(){e.isUserSignedIn&&b.location.assign("/#/main")}}]),angular.module("advApp").controller("MainCtrl",function(){var a=this;a.a_date=new Date,a["goto"]=function(a,b){setTimeout(function(){window.location.replace(b)},150)},a.searchButtons=[{name:"search_strategies.inexpensive.title",href:"#/search?strategy=inexpensive",description:"search_strategies.inexpensive.description",color:"red"},{name:"search_strategies.friends.title",href:"#/search?strategy=friends",description:"search_strategies.friends.description",color:"purple"},{name:"search_strategies.sat_night_fever.title",href:"#/search?strategy=saturday_night_fever",description:"search_strategies.sat_night_fever.description",color:"blue"},{name:"search_strategies.couples.title",href:"#/search?strategy=couples",description:"search_strategies.couples.description",color:"orange"},{name:"search_strategies.surprise_me.title",href:"#/search?strategy=surprise_me",description:"search_strategies.surprise_me.description",color:"green "}]}),angular.module("advApp").controller("ProfileCtrl",["$scope","API",function(a,b){function c(b){b.forEach(function(b){b.checked=a.user.tags.indexOf(b.id)>-1;var c=b.category;a.tags.push(b),c.id<5&&a.categories.indexOf(c.name)<0&&a.categories.push(c.name)})}a.loadUser=function(){a.tags=[],a.user={},a.categories=[],b.resource("/users/:id").get({id:1}).$promise.then(function(b){a.user=b,console.log(b)}).then(function(){b.resource("/tags").query().$promise.then(c)})},a.selectTag=function(b){var c=a.user.tags.indexOf(b);$("#chk"+b).prop("checked")?c&&a.user.tags.push(b):a.user.tags.splice(c,1),console.log(a.user.tags)},a.loadUser()}]),angular.module("advApp").controller("FriendsCtrl",["$scope","API",function(a,b){a.loadUser=function(){a.tags=[],a.user={},a.categories=[],b.resource("/users/:id").get({id:1}).$promise.then(function(b){a.user=b,console.log(b)}).then(function(){b.resource("/tags").query().$promise.then(loadTags)})},a.loadUser()}]),angular.module("advApp").controller("SearchCtrl",function(){}),angular.module("advApp").controller("LocaleCtrl",["$translate","$scope","tmhDynamicLocale",function(a,b,c){b.changeLanguage=function(b){a.use(b),c.set(b)}}]),angular.module("advApp").filter("money",["$translate",function(a){return function(b){var c="en"===a.use();return c?b/15:b}}]),function(){function a(b,c,d){function e(a){if(c.isUserSignedIn){var e=d.host(),f="localhost"===e?":8080/api":"/api";return b("http://"+e+f+a,null,{get:{method:"GET"},save:{method:"POST"},update:{method:"PUT"},query:{method:"GET",isArray:!0},remove:{method:"DELETE"},"delete":{method:"DELETE"}})}}function f(){e("/users/byEmail/:email",{email:c.currentUser.email}).get().$promise.then(function(a){c.user=a},function(){})}a.$inject=["$resource","$rootScope","$location"];var g={resource:e,getOrCreateUser:f};return g}a.$inject=["$resource","$rootScope","$location"],angular.module("advApp").factory("API",a)}(),angular.module("advApp").run(["$templateCache",function(a){a.put("views/friends.html",'<div class="card-panel"> <h5 translate> friends </h5> <div class="divider"></div> <div class="row section"> <ul class="pagination"> <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li> <li class="active"><a href="#!">1</a></li> <li class="waves-effect"><a href="#!">2</a></li> <li class="waves-effect"><a href="#!">3</a></li> <li class="waves-effect"><a href="#!">4</a></li> <li class="waves-effect"><a href="#!">5</a></li> <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li> </ul> </div> </div>'),a.put("views/login.html",'<div class="container center-align" ng-init="checkLogin()"> <div class="row section" style="height:400px"> <a class="btn red" style="margin-top:100px" g-login style="text-transform:none !important"><i class="fa fa-google left"></i>{{"google_sign_in" | translate}}</a> </div> </div>'),a.put("views/main.html",'<div class="row"> <div class="col s12 m12 l6 center-align" ng-repeat="btn in main.searchButtons"> <div class="main-btn waves-effect waves-light card-panel small hoverable opacity-80 txt-shadow {{btn.color}}" ng-click="main.goto(btn.name, btn.href)"> <span class="white-text"> <h5 translate>{{btn.name}}</h5> <p translate>{{btn.description}}</p> </span> </div> </div> </div> <p>{{$location.host()}}</p>'),a.put("views/profile.html",'<div class="card-panel"> <h5 translate> profile </h5> <div class="divider"></div> <div class="row section"> <div input-field class="col s12 m6 l6"> <label translate>name</label> <input type="text" ng-model="user.name"> </div> <div input-field class="col s12 m6 l6"> <label translate>surname</label> <input type="text" ng-model="user.surname"> </div> <div input-field class="col s12 m6 l6"> <label translate>email</label> <input type="text" ng-model="user.email"> </div> </div> <div class="divider"></div> <div class="row section"> <div input-field class="col s7 m4 l3"> <label translate>inexpensive_limit</label> <input type="text" ng-model="user.inexpensiveOutingLimit"> </div> </div> <div class="divider"></div> <div class="row section"> <div class="col s12 m6 l5 offset-l1" ng-repeat="c in categories"> <h6 translate="tag_categories.{{c}}"></h6> <div ng-repeat="t in tags | filter: {category: {name: c}} "> <input type="checkbox" id="chk{{t.id}}" ng-model="t.checked" ng-change="selectTag(t.id)"> <label for="chk{{t.id}}">{{t.name}}</label> </div> </div> </div> <div class="divider"></div> <div class="row section"> <div class="col s12 center-align"> <a class="waves-effect waves-light btn" ng-click="user.$update()">GUARDAR <i class="material-icons right">save</i></a> </div> </div> </div>'),a.put("views/search.html",'<p><h3>Sección de búsqueda</h3></p> <p ng-repeat="u in search.users">{{u.name}}</p>')}]);