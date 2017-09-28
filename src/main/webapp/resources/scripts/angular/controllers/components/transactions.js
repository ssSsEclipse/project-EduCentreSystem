var app = angular.module('app');

app.controller('TransactionsCtrl', ['$scope','TransactionsService','$translate','$filter', 'CommonFactory', '$rootScope', 'CentreService', 'UsersService', 
                                    function ($scope,TransactionsService,$translate,$filter,CommonFactory,$rootScope,CentreService,UsersService) {
	var $filterTranslate = $filter('translate');
	var paginationOptions = {
     pageNumber: 1,
	 pageSize: 100,
	 sort: null
   };
	$scope.centreId = '';
	
	$('.ui.dropdown').dropdown();
	
	$('#tutorialCentreId').on('change', function() {
		$scope.centreId = $('#tutorialCentreId').val();
	});

   $scope.getTransactions = function(){
	   TransactionsService.getTransactions($scope.centreId, paginationOptions.pageNumber, paginationOptions.pageSize).success(function(data){
		  $scope.gridOptions.data = data.result.content;
	 	  $scope.gridOptions.totalItems = data.result.totalElements;
	 	  $scope.grandTotal = data.grandTotal;
	   });    
	}
	
   if (CommonFactory.hasRole($rootScope.user, 'ROLE_USER')) {
	   $scope.userId = $rootScope.user.id;
	   CentreService.getCentreByCurrentUser()
   		.then( function success(response) {
			   $scope.centreId = response.data.id;
			   $scope.getTransactions();
		   },
		   function error(response) {
			   CommonFactory.buildResponseMessage($scope.responseMessage, $filterTranslate(response.data.message), 'error');
		   });
   }else if (CommonFactory.hasRole($rootScope.user, 'ROLE_ADMIN')) {
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
		$scope.getTransactions();
   }
   
   $scope.gridOptions = {
    paginationPageSizes: [50, 100, 200],
    paginationPageSize: paginationOptions.pageSize,
    enableColumnMenus:false,
	enableFiltering: true,
	useExternalPagination: true,
    columnDefs: [
      { name: 'id', displayName:'views.transaction.id', headerCellFilter:'translate', width: "7%" },
      { name: 'recordTime', displayName:'views.transaction.recordTime', headerCellFilter:'translate'
    	  , width: "14%", type: 'date', cellFilter: 'date:"yyyy-MM-dd HH:mm:ss"' },
      { name: 'customerName', displayName:'views.transaction.customerName', headerCellFilter:'translate', width: "12%" },
      { name: 'content', displayName:'views.transaction.content', headerCellFilter:'translate', width: "22%" },,
      { name: 'chequeIssuedDate', displayName:'views.transaction.chequeIssuedDate', headerCellFilter:'translate', width: "10%", type: 'date', cellFilter: 'date:"yyyy-MM-dd"' },
      { name: 'chequeId', displayName:'views.transaction.chequeId', headerCellFilter:'translate', width: "15%" },
      { name: 'amount', displayName:'views.transaction.amount', headerCellFilter:'translate', width: "10%", cellFilter: 'currency' },
      { name: 'commission', displayName:'views.transaction.commission', headerCellFilter:'translate', width: "10%", cellFilter: 'currency' }
    ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
          
        });
     }
  };
   
  
}]);


app.service('TransactionsService',['$http', function ($http) {
	
	function getTransactions(centreId, pageNumber, size) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'transactions/list?centreId='+centreId+'&page='+pageNumber+'&size='+size+'&month='+$('input[name=month').val()+'&year='+$('input[name=year').val()
        });
    }
	
    return {
    	getTransactions:getTransactions
    };
	
}]);