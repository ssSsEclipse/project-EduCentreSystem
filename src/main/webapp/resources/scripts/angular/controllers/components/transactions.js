var app = angular.module('app');

app.controller('TransactionsCtrl', ['$scope','TransactionsService','$translate','$filter', function ($scope,TransactionsService,$translate,$filter) {
	var $filterTranslate = $filter('translate');
	var paginationOptions = {
     pageNumber: 1,
	 pageSize: 10,
	 sort: null
   };
	
	$('.ui.dropdown').dropdown();

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
      { name: 'id', displayName:'views.transaction.id', headerCellFilter:'translate', width: "7%" },
      { name: 'recordTime', displayName:'views.transaction.recordTime', headerCellFilter:'translate'
    	  , width: "14%", type: 'date', cellFilter: 'date:"yyyy-MM-dd HH:mm:ss"' },
      { name: 'customerName', displayName:'views.transaction.customerName', headerCellFilter:'translate', width: "12%" },
      { name: 'content', displayName:'views.transaction.content', headerCellFilter:'translate', width: "47%" },
      { name: 'amount', displayName:'views.transaction.amount', headerCellFilter:'translate', width: "10%", cellFilter: 'currency' },
      { name: 'commission', displayName:'views.transaction.commission', headerCellFilter:'translate', width: "10%", cellFilter: 'currency' }
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
          url: 'transactions/list?page='+pageNumber+'&size='+size+'&month='+$('input[name=month').val()+'&year='+$('input[name=year').val()
        });
    }
	
    return {
    	getTransactions:getTransactions
    };
	
}]);