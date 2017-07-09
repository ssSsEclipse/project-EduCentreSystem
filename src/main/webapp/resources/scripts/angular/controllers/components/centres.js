var app = angular.module('app');

app.controller('CentresCtrl', ['$scope','CentreService','CommonFactory','$translate','$filter', function ($scope,CentreService,CommonFactory,$translate,$filter) {
	var $filterTranslate = $filter('translate');
	var paginationOptions = {
			pageNumber: 1,
			pageSize: 10,
			sort: null
	};
   
   $scope.gridOptions = {
		   paginationPageSizes: [5, 10, 20],
		   paginationPageSize: paginationOptions.pageSize,
		   enableColumnMenus: false,
		   enableFiltering: true,
		   enableHorizontalScrollbar: 1,
		   useExternalPagination: true,
		   columnDefs: [
                { name: 'id', field: 'id', visible: false},
                { name: 'schoolName', minWidth: 200, displayName:'views.centre.schoolName', headerCellFilter:'translate'},
                { name: 'schoolAddress', minWidth: 200, displayName:'views.centre.schoolAddress', headerCellFilter:'translate'},
                { name: 'schoolPhone', minWidth: 150, displayName:'views.centre.schoolPhone', headerCellFilter:'translate' },
                { name: 'schoolFax', minWidth: 100, displayName:'views.centre.schoolFax', headerCellFilter:'translate' },
                { name: 'email', minWidth: 200, displayName:'views.centre.email', headerCellFilter:'translate' },
                { name: 'website', minWidth: 200, displayName:'views.centre.website', headerCellFilter:'translate' },
                { name: 'institutionPic', minWidth: 200, displayName:'views.centre.institutionPic', headerCellFilter:'translate' },
                { name: 'picMobile', minWidth: 100, displayName:'views.centre.picMobile', headerCellFilter:'translate' },
                { name: 'bankName', minWidth: 150, displayName:'views.centre.bankName', headerCellFilter:'translate' },
                { name: 'accountName', minWidth: 150, displayName:'views.centre.accountName', headerCellFilter:'translate' },
                { name: 'accountNumber', minWidth: 150, displayName:'views.centre.accountNumber', headerCellFilter:'translate' },
                { name: 'couponCode', minWidth: 300, displayName:'views.centre.couponCode', headerCellFilter:'translate' },
                { name: 'discountComissionPdf', minWidth: 200, displayName:'views.centre.discountComissionPdf', headerCellFilter:'translate' },
                { name: 'createDateTime', minWidth: 170, type: 'date', cellFilter: 'date:"yyyy-MM-dd hh:mm:ss"', enableFiltering: false
                	, displayName:'column.header.createDateTime', headerCellFilter:'translate'},
                { name: 'modifiedDateTime', minWidth: 170, type: 'date', cellFilter: 'date:"yyyy-MM-dd hh:mm:ss"', enableFiltering: false
                	, displayName:'column.header.modifiedDateTime', headerCellFilter:'translate'},
                { name: 'actions', minWidth: 70, enableFiltering: false, cellTemplate: 
            		'<div class="grid-action-cell">'+
            		'<a ng-click="grid.appScope.editSelectedRow(row.entity);" href="#"><i class="edit icon"></i></a>'+
            		'<a ng-click="grid.appScope.deleteCentre(row.entity.id);" href="#"><i class="red remove icon"></i></a>'+
            		'</div>'
            		, displayName:'column.header.actions', headerCellFilter:'translate'
                }
           ],
           onRegisterApi: function(gridApi) {
        	   $scope.gridApi = gridApi;
        	   gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
        		   paginationOptions.pageNumber = newPage;
        		   paginationOptions.pageSize = pageSize;
          
        		   CentreService.getAllCentres(newPage,pageSize).success(function(data){
        			   $scope.gridOptions.data = data.content;
        			   $scope.gridOptions.totalItems = data.totalElements;
        		   });
        	   });
           }
   	};

   $scope.editMode = false;
   $scope.centreForm = $('#centreForm');
   $scope.responseMessage = $('#responseMessage');
   
	/*var formValidationRules = {
		fields : {
			schoolName : {
				identifier : 'schoolName',
				rules : [ {
					type : 'empty',
					prompt : $filterTranslate('form.validation.required', { fieldName: '{name}' })
				} ]
			},
			schoolPhone : {
				identifier : 'schoolPhone',
				rules : [ {
					type : 'number',
					prompt : $filterTranslate('views.centre.form.validation.phone.invalid')
				},{
					type : 'maxLength[8]',
					prompt : $filterTranslate('views.centre.form.validation.phone.invalid')
				}]
			},
			schoolFax : {
				identifier : 'schoolFax',
				rules : [ {
					type : 'number',
					prompt : $filterTranslate('views.centre.form.validation.phone.invalid')
				},{
					type : 'maxLength[8]',
					prompt : $filterTranslate('views.centre.form.validation.phone.invalid')
				}]
			}
		}
	};*/
	$('.ui.form').form();
   
   $scope.generateCouponCode = function() {
	   var uuid = function generateUUID() {
		    var d = new Date().getTime();
		    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
		        var r = (d + Math.random()*16)%16 | 0;
		        d = Math.floor(d/16);
		        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
		    });
		    return uuid;
		};
		$('#couponCode').val(uuid());
   }
   
   $scope.downloadPdf = function(filename) {
       $http.get('resources/pdf/'+filename).then(function(response) {
           $(anchor).attr({
               href: 'data:application/pdf;base64,' + response.data,
               download: attr.filename
           })
               .removeAttr('disabled')
               .text('Save')
               .removeClass('btn-primary')
               .addClass('btn-success');
       });
   }
   
   $scope.editSelectedRow = function(centre) {
	   if ($scope.centreForm != null) {
		   $scope.editMode = true;
		   $scope.editingCentre = centre.schoolName;
		   CommonFactory.resetResponseMessage($scope.responseMessage)
		   CommonFactory.putDataToForm(centre, $scope.centreForm);
	   }
   }
   
   $scope.resetForm = function() {
	   $scope.editMode = false;
       CommonFactory.resetForm($scope.centreForm);
   }
   
   $scope.submitForm = function() {
	   if ($scope.centreForm != null) {
		   if ($($scope.centreForm).form('is valid')) {
			   if ($scope.editMode) {
				   $scope.updateCentre();
			   }else {
				   $scope.addCentre();
			   }
		   }
	   }
   }
   
	$scope.addCentre = function () {
       if ($scope.centreForm != null) {
    	   CentreService.addCentre($scope.centreForm)
             .then (function success(response){
                 CommonFactory.buildResponseMessage($scope.responseMessage, response.data.message, 'success');
                 $scope.getAllCentres();
                 $scope.resetForm();
             },
             function error(response){
            	 $scope.errorMessage = response.data.message;
                 CommonFactory.buildResponseMessage($scope.responseMessage, response.data.message, 'error');
                 $scope.getAllCentres();
           });
       }
   }
	
	$scope.updateCentre = function () {
		CentreService.updateCentre($scope.centreForm)
          .then(function success(response){
              CommonFactory.buildResponseMessage($scope.responseMessage, response.data.message, 'success');
              $scope.getAllCentres();
              $scope.resetForm();
          },
          function error(response){
              CommonFactory.buildResponseMessage($scope.responseMessage, response.data.message, 'error');
              $scope.getAllCentres();
          });
    }
	

   $scope.deleteCentre = function (id) {
	   CentreService.deleteCentre(id)
          .then (function success(response){
              CommonFactory.buildResponseMessage($scope.responseMessage, response.data.message, 'success');
              $scope.getAllCentres();
          },
          function error(response){
              CommonFactory.buildResponseMessage($scope.responseMessage, response.data.message, 'error');
              $scope.getAllCentres();
          })
    }

   $scope.getAllCentres = function () {
	   CentreService.getAllCentres(paginationOptions.pageNumber, paginationOptions.pageSize)
         .then(function success(response){
   		     $scope.gridOptions.data = response.data.content;
   	 	     $scope.gridOptions.totalItems = response.data.totalElements;
         },
         function error (response ){
             console.log(response)
         });
   }
   
   $scope.getAllCentres();
  
}]);
app.service('CentreService',['$http','CommonFactory', function ($http,CommonFactory) {
    this.getCentre = function getCentre(centreId){
        return $http({
          method: 'GET',
          url: 'centres/'+centreId
        });
	}
	
    this.addCentre = function addCentre(centreForm){
        return $http({
          method: 'POST',
          url: 'centres',
          data: CommonFactory.formDataToJSONString(centreForm)
        });
    }
	
    this.deleteCentre = function deleteCentre(id){
        return $http({
          method: 'DELETE',
          url: 'centres/'+id
        })
    }
	
    this.updateCentre = function updateCentre(centreForm){
        return $http({
          method: 'PATCH',
          url: 'centres/',
          data: CommonFactory.formDataToJSONString(centreForm)
        })
    }
	
    this.getAllCentres = function getAllCentres(pageNumber, size) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'centres/list?page='+pageNumber+'&size='+size
        });
    }
}]);