var app = angular.module('app');

app.controller('AppCtrl', ['$translate', '$scope', 'AppService', '$rootScope', 'CommonFactory', '$location', function ($translate, $scope, AppService, $rootScope, CommonFactory, $location) {
 
  $scope.changeLanguage = function (langKey) {
    $translate.use(langKey);
  };

  AppService.getCurrentUser()
    .then(function success(response){
    	$rootScope.user = response.data;
    },
    function error (response ){
        console.log(response);
  });
  
  $scope.hasRole = function(role) {
	  return CommonFactory.hasRole($rootScope.user, role);
  }
  

 /* $rootScope.$on("$routeChangeStart", function(evt, to, from){
      var hasRole = false;
      if (!from) {
    	  $location.path('/');
      }
      for(var i in to.roles) {
          if($scope.hasRole(to.roles[i])) {
        	  hasRole = true;
          }
      }
      if (!hasRole) {
    	  evt.preventDefault();
      }
  });*/
  
}]);
app.service('AppService',['$http',function ($http) {
    this.getCurrentUser = function getCurrentUser(){
        return $http({
          method: 'GET',
          url: 'currentUser'
        });
	}
}]);