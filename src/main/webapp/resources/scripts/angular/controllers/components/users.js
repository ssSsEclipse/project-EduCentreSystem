var app = angular.module('app');

app.controller('UsersCtrl', ['$scope','UsersService','CommonFactory','$translate','$filter', function ($scope,UsersService,CommonFactory,$translate,$filter) {
	var $filterTranslate = $filter('translate');
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
                { name: 'username', width: "13%", displayName:'views.user.username', headerCellFilter:'translate'},
                { name: 'password', width: "20%", displayName:'views.user.password', headerCellFilter:'translate'},
                { name: 'active', width: "7%", displayName:'views.user.active', headerCellFilter:'translate' },
                { name: 'role', width: "10%", displayName:'views.user.role', headerCellFilter:'translate' },
                { name: 'tutorialCentreId', width: "13%", displayName:'views.user.tutorialCentre', 
                	headerCellFilter:'translate', 
                	cellTemplate:'<div class="ui-grid-cell-contents" ng-if="row.entity.tutorialCentre">{{row.entity.tutorialCentre.schoolName}}</div>' +
                    			'<div class="ui-grid-cell-contents" ng-if="!row.entity.tutorialCentre"></div>'
                	},
                { name: 'createDateTime', width: "15%", type: 'date', cellFilter: 'date:"yyyy-MM-dd HH:mm:ss"', enableFiltering: false
                	, displayName:'column.header.createDateTime', headerCellFilter:'translate'},
                { name: 'modifiedDateTime', width: "15%", type: 'date', cellFilter: 'date:"yyyy-MM-dd HH:mm:ss"', enableFiltering: false
                	, displayName:'column.header.modifiedDateTime', headerCellFilter:'translate'},
                { name: 'actions', width: "7%", enableFiltering: false, cellTemplate: 
            		'<div class="grid-action-cell">'+
            		'<a ng-click="grid.appScope.editSelectedRow(row.entity);" href=""><i class="edit icon"></i></a>'+
            		'<a ng-click="grid.appScope.deleteUserConfirm(row.entity.id);" href=""><i class="red remove icon"></i></a>'+
            		'</div>'
            		, displayName:'column.header.actions', headerCellFilter:'translate'
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
   $scope.editMode = false;

	$('#activeCheckboxDiv').checkbox( {
		onChange : function() {
			$('#activeLabel').html($('#activeCheckbox').prop('checked') ? $filterTranslate('views.user.active.true') : $filterTranslate('views.user.active.false'));
		}
	});
	$('#roleDropdown').dropdown();
	UsersService.getCentreList().then(function success(response){
		var select = $('#tutorialCentreId');
		if (response.data.success && response.data.results) {
			$(response.data.results).each(function(index, item) {
				select.append($("<option></option>")
	                    .attr("value",item.value)
	                    .text(item.name)); 
			});
		}
		
		select.dropdown("refresh");
    });
	
	$('#userForm').form({
	    fields: {
	        username: {
	          rules: [
	            {
	              type   : 'empty',
	              prompt : $filterTranslate('views.user.form.validation.username.required')
	            }
	          ]
	        },
	        password: {
	          rules: [
	            {
	              type   : 'empty',
	              prompt : $filterTranslate('views.user.form.validation.password.required')
	            }
	          ]
	        }
	      }
	    }
	);
   
   $scope.editSelectedRow = function(user) {
	   if ($scope.userForm != null) {
		   $scope.editMode = true;
		   $scope.editingUser = user.username;
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
                 CommonFactory.buildResponseMessage($scope.responseMessage, $filterTranslate(response.data.message), 'success');
                 $scope.getAllUsers();
                 $scope.resetForm();
             },
             function error(response){
            	 $scope.errorMessage = response.data.message;
                 CommonFactory.buildResponseMessage($scope.responseMessage, $filterTranslate(response.data.message), 'error');
                 $scope.getAllUsers();
           });
       }
   }
	
	$scope.updateUser = function () {
		UsersService.updateUser($scope.userForm)
          .then(function success(response){
              CommonFactory.buildResponseMessage($scope.responseMessage, $filterTranslate(response.data.message), 'success');
              $scope.getAllUsers();
              $scope.resetForm();
          },
          function error(response){
              CommonFactory.buildResponseMessage($scope.responseMessage, $filterTranslate(response.data.message), 'error');
              $scope.getAllUsers();
          });
    }
	

   $scope.deleteUserConfirm = function (id) {
	   CommonFactory.confirmDelete($('#deleteConfirmModal'), 
			   $scope.deleteUser , 
		      [id]);
    }
   
   $scope.deleteUser = function(id) {
	   UsersService.deleteUser(id)
          .then (function success(response){
              CommonFactory.buildResponseMessage($scope.responseMessage, $filterTranslate(response.data.message), 'success');
              $scope.getAllUsers();
          },
          function error(response){
              CommonFactory.buildResponseMessage($scope.responseMessage, $filterTranslate(response.data.message), 'error');
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
    
    this.getCentreList = function getCentreList() {
        return  $http({
          method: 'GET',
          url: 'centres/simpleList'
        });
    }
}]);