<html ng-app="app">
<head>
<title>Transaction</title>
<link rel="stylesheet" type="text/css" href="webjars/Semantic-UI/2.2.10/semantic.min.css" charset="UTF-8">
<script src="webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="webjars/Semantic-UI/2.2.10/semantic.min.js"></script>

<link rel="stylesheet" href="webjars/angular-ui-grid/4.0.6/ui-grid.min.css">
<script src="webjars/angularjs/1.5.6/angular.min.js"></script>
<script src="webjars/angular-ui-grid/4.0.6/ui-grid.min.js"></script>

<script src="resources/scripts/angular/app.js"></script>
<script src="resources/scripts/angular/component/transactions.js"></script>
<script type="text/javascript">
$(function() {
    $('.ui.dropdown').dropdown();
    $('.ui.form').form({
		fields : {
			year : {
				identifier : 'year',
				rules : [ {
					type : 'empty',
					prompt : 'Please enter year'
				}, {
		            type   : 'number',
					prompt : 'Please enter a valid year'
				}, {
		            type   : 'regExp[/^[12][0-9]{3}$/]',
					prompt : 'Please enter a valid year'
				}  ]
			},
			month : {
				identifier : 'month',
				rules : [ {
					type : 'empty',
					prompt : 'Please select month'
				}]
			}
		}
	});
});
</script>
</head>
<body>
	<jsp:include page="component/userHeader.jsp" />

	<div class="ui main text container">
		<h1 class="ui dividing header">Search</h1>
		
		<%-- Search Form --%>
		<form class="ui form" id="searchTransactionForm">
			<div class="fields">
				<div class="eight wide field">
					<div class="three fields">
						<div class="field">
							<select class="ui fluid search selection dropdown" name="month">
								<option value="">Month</option>
								<option value="1">January</option>
								<option value="2">February</option>
								<option value="3">March</option>
								<option value="4">April</option>
								<option value="5">May</option>
								<option value="6">June</option>
								<option value="7">July</option>
								<option value="8">August</option>
								<option value="9">September</option>
								<option value="10">October</option>
								<option value="11">November</option>
								<option value="12">December</option>
							</select>
						</div>
						<div class="field">
							<input type="text" name="year" maxlength="4" placeholder="Year">
						</div>
						<div class="field">
							<div class="ui button submit" onclick="getTransactions(0, 10); return false;">Search</div>
						</div>
					</div>
				</div>
			</div>
			<div class="ui error message"></div>
		</form>

		<%-- Result Table --%>
		<h1 class="ui dividing header">Result</h1>
		<div  ng-controller="TransactionsCtrl">
			<div ui-grid="gridOptions" ui-grid-pagination></div>
		</div>
	</div>
</body>
</html>
