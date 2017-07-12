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
  
}]);
app.service('AppService',['$http',function ($http) {
    this.getCurrentUser = function getCurrentUser(){
        return $http({
          method: 'GET',
          url: 'currentUser'
        });
	}
}]);