var app = angular.module('app');

app.controller('TransactionsCtrl', ['$scope','TransactionsService', function ($scope,TransactionsService) {
   var paginationOptions = {
     pageNumber: 1,
	 pageSize: 10,
	 sort: null
   };

   $scope.getTransactions = function(){
	   TransactionsService.getTransactions(paginationOptions.pageNumber, paginationOptions.pageSize).success(function(data){
		  $scope.gridOptions.data = data.content;
	 	  $scope.gridOptions.totalItems = data.totalElements;
	   });    
	}
   
   $scope.gridOptions = {
    paginationPageSizes: [5, 10, 20],
    paginationPageSize: paginationOptions.pageSize,
    enableColumnMenus:false,
	useExternalPagination: true,
    columnDefs: [
      { name: 'recordTime', width: "*", type: 'date', cellFilter: 'date:\'yyyy-MM-dd\'' },
      { name: 'customerName', width: "*" },
      { name: 'content', width: "*" },
      { name: 'amount', width: "*" },
      { name: 'commission', width: "*" }
    ],
    onRegisterApi: function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
          paginationOptions.pageNumber = newPage;
          paginationOptions.pageSize = pageSize;
          
          TransactionsService.getTransactions(newPage,pageSize).success(function(data){
        	  $scope.gridOptions.data = data.content;
         	  $scope.gridOptions.totalItems = data.totalElements;
          });
        });
     }
  };
   
   $scope.getTransactions();
  
}]);


app.service('TransactionsService',['$http', function ($http) {
	
	function getTransactions(pageNumber, size) {
		pageNumber = pageNumber > 0?pageNumber - 1:0;
        return  $http({
          method: 'GET',
          url: 'transactions/list?page='+pageNumber+'&size='+size+'&month='+$('select[name=month').val()+'&year='+$('input[name=year').val()
        });
    }
	
    return {
    	getTransactions:getTransactions
    };
	
}]);