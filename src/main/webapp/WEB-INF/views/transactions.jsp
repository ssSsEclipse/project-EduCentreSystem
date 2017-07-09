<!doctype html>
<html ng-app="app">
<head>
<title>{{'views.header.transaction' | translate}}</title>
<link rel="stylesheet" type="text/css" href="webjars/Semantic-UI/2.2.10/semantic.min.css" charset="UTF-8">
<script src="webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="webjars/Semantic-UI/2.2.10/semantic.min.js"></script>

<link rel="stylesheet" href="webjars/angular-ui-grid/4.0.6/ui-grid.min.css">
<script src="webjars/angularjs/1.5.6/angular.min.js"></script>
<script src="webjars/angular-ui-grid/4.0.6/ui-grid.min.js"></script>
<script src="webjars/angular-translate/2.13.1/angular-translate.min.js"></script>
<script src="webjars/angular-translate-loader-url/2.13.1/angular-translate-loader-url.min.js"></script>
<script src="webjars/angular-translate-loader-static-files/2.13.1/angular-translate-loader-static-files.min.js"></script>

<script src="resources/scripts/angular/app.js"></script>
<script src="resources/scripts/angular/controllers/appCtrl.js"></script>
<script src="resources/scripts/angular/controllers/components/transactions.js"></script>
<style>
.grid {
	height: 400px;
}

.main {
	width: 900px;
}
</style>
</head>
<body ng-controller="AppCtrl">
	<jsp:include page="component/header.jsp" />

	<div class="ui main container" ng-controller="TransactionsCtrl">
		<h1 class="ui dividing header">{{'views.header.transaction' | translate}}</h1>

		<%-- Search Form --%>
		<form class="ui form segment" id="searchTransactionForm">
			<div class="fields">
				<div class="eight wide field">
					<h4 class="ui dividing header">{{'views.transaction.recordTime' | translate}}</h4>
					<div class="two fields">
						<div class="inline field">
							<label>{{'views.transaction.month' | translate}}</label>
							<div class="ui search selection dropdown">
								<input type="hidden" name="month"> <i class="dropdown icon"></i>
								<div class="default text"></div>
								<div class="menu">
									<div class="item" data-value="1">1</div>
									<div class="item" data-value="2">2</div>
									<div class="item" data-value="3">3</div>
									<div class="item" data-value="4">4</div>
									<div class="item" data-value="5">5</div>
									<div class="item" data-value="6">6</div>
									<div class="item" data-value="7">7</div>
									<div class="item" data-value="8">8</div>
									<div class="item" data-value="9">9</div>
									<div class="item" data-value="10">10</div>
									<div class="item" data-value="11">11</div>
									<div class="item" data-value="12">12</div>
								</div>
							</div>
						</div>
						<div class="inline field">
							<label>{{'views.transaction.year' | translate}}</label> <input type="text" name="year" maxlength="4">
						</div>
					</div>
					<div class="ui button" ng-click="getTransactions()">
						<i class="search icon"></i>{{'button.search' | translate}}
					</div>
				</div>
			</div>
			<div class="ui error message"></div>
		</form>

		<h1 class="ui dividing header">{{'views.subheader.list' | translate}}</h1>
		<div>
			<div id="grid1" ui-grid="gridOptions" ui-grid-pagination class="grid"></div>
		</div>
	</div>

</body>
</html>
