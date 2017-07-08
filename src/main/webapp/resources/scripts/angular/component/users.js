var app = angular.module('app');

app.controller('UsersCtrl', ['$scope','UsersService','CommonFactory', function ($scope,UsersService,CommonFactory) {
	var paginationOptions = {
			pageNumber: 1,
			pageSize: 10,
			sort: null
	};
   
   $scope.gridOptions = {
		   paginationPageSizes: [5, 10, 20],
		   paginationPageSize: paginationOptions.pageSize,
		   enableColumnMenus:false,
		   enableFiltering: true,
		   useExternalPagination: true,
		   columnDefs: [
		                { name: 'id', field: 'id', visible: false},
		                { name: 'username', width: "13%" },
		                { name: 'password', width: "20%" },
		                { name: 'active', width: "7%" },
		                { name: 'role', width: "10%" },
		                { name: 'tutorialCentreId', width: "13%" },
		                { name: 'createDateTime', width: "15%", type: 'date', cellFilter: 'date:"yyyy-MM-dd hh:mm:ss"', enableFiltering: false},
		                { name: 'modifiedDateTime', width: "15%", type: 'date', cellFilter: 'date:"yyyy-MM-dd hh:mm:ss"', enableFiltering: false },
		                { name: 'actions', displayName: 'Actions', width: "7%", enableFiltering: false, cellTemplate: 
	                		'<div class="grid-action-cell">'+
	                		'<a ng-click="grid.appScope.editSelectedRow(row.entity);" href="#"><i class="edit icon"></i></a>'+
	                		'<a ng-click="grid.appScope.deleteUser(row.entity.id);" href="#"><i class="red remove icon"></i></a>'+
	                		'</div>'
		                }
           ],
           onRegisterApi: function(gridApi) {
        	   $scope.gridApi = gridApi;
        	   gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
        		   paginationOptions.pageNumber = newPage;
        		   paginationOptions.pageSize = pageSize;
          
        		   UsersService.getAllUsers(newPage,pageSize).success(function(data){
        			   $scope.gridOptions.data = data.content;
        			   $scope.gridOptions.totalItems = data.totalElements;
        		   });
        	   });
           }
   	};
   
   $scope.userForm = $('#userForm');
   $scope.responseMessage = $('#responseMessage');
   
   $scope.editSelectedRow = function(user) {
	   if ($scope.userForm != null) {
		   $scope.editMode = true;
		   CommonFactory.resetResponseMessage($scope.responseMessage)
		   CommonFactory.putDataToForm(user, $scope.userForm);
	   }
   }
   
   $scope.resetForm = function() {
	   $scope.editMode = false;
       CommonFactory.resetForm($scope.userForm);
   }
   
   $scope.submitForm = function() {
	   if ($scope.userForm != null) {
		   if ($($scope.userForm).form('is valid')) {
			   if ($scope.editMode) {
				   $scope.updateUser();
			   }else {
				   $scope.addUser();
			   }
		   }
	   }
   }
   
	$scope.addUser = function () {
       if ($scope.userForm != null) {
    	   UsersService.addUser($scope.userForm)
             .then (function success(response){
                 CommonFactory.buildResponseMessage($scope.responseMessage, response.data.message, 'success');
                 $scope.getAllUsers();
                 $scope.resetForm();
             },
             function error(response){
            	 $scope.errorMessage = response.data.message;
                 CommonFactory.buildResponseMessage($scope.responseMessage, response.data.message, 'error');
                 $scope.getAllUsers();
           });
       }
   }
	
	$scope.updateUser = function () {
		UsersService.updateUser($scope.userForm)
          .then(function success(response){
              CommonFactory.buildResponseMessage($scope.responseMessage, response.data.message, 'success');
              $scope.getAllUsers();
              $scope.resetForm();
          },
          function error(response){
              CommonFactory.buildResponseMessage($scope.responseMessage, response.data.message, 'error');
              $scope.getAllUsers();
          });
    }
	

   $scope.deleteUser = function (id) {
	   UsersService.deleteUser(id)
          .then (function success(response){
              CommonFactory.buildResponseMessage($scope.responseMessage, response.data.message, 'success');
              $scope.getAllUsers();
          },
          function error(response){
              CommonFactory.buildResponseMessage($scope.responseMessage, response.data.message, 'error');
              $scope.getAllUsers();
          })
    }

   $scope.getAllUsers = function () {
	   UsersService.getAllUsers(paginationOptions.pageNumber, paginationOptions.pageSize)
         .then(function success(response){
   		     $scope.gridOptions.data = response.data.content;
   	 	     $scope.gridOptions.totalItems = response.data.totalElements;
         },
         function error (response ){
             console.log(response)
         });
   }
   
   $scope.getAllUsers();
   $scope.editMode = false;
  
}]);
app.service('UsersService',['$http','CommonFactory', function ($http,CommonFactory) {
    this.getUser = function getUser(userId){
        return $http({
          method: 'GET',
          url: 'users/'+userId
        });
	}
	
    this.addUser = function addUser(userForm){
        return $http({
          method: 'POST',
          url: 'users',
          data: CommonFactory.formDataToJSONString(userForm)
        });
    }
	
    this.deleteUser = function deleteUser(id){
        return $http({
          method: 'DELETE',
          url: 'users/'+id
        })
    }
	
    this.updateUser = function updateUser(userForm){
        return $http({
          method: 'PATCH',
          url: 'users/',
          data: CommonFactory.formDataToJSONString(userForm)
        })
    }
	
    this.getAllUsers = function getAllUsers(pageNumber, size) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'users/list?page='+pageNumber+'&size='+size
        });
    }
}]);