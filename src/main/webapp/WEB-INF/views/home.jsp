<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<html ng-app="app">
<head>
<title>{{"views.home.appname" | translate}}</title>
<link rel="stylesheet" type="text/css" href="webjars/Semantic-UI/2.2.10/semantic.min.css" charset="UTF-8">
<link rel="stylesheet" type="text/css" href="webjars/angular-ui-grid/4.0.6/ui-grid.min.css">
<link rel="stylesheet" type="text/css" href="resources/styles/header.css">
<style>
[ng\:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak, .x- ng-cloak
	{
	display: none !important;
}

.ui-grid-header-cell-wrapper {
	max-height: 50px;
}

.grid {
	max-height: 500px;
}
</style>
</head>
<body ng-controller="AppCtrl" ng-cloak>
	<div class="ui fixed inverted menu">
		<div class="ui container">
			<a href="#" class="header item"><i class="circular inverted teal book icon"></i>{{'views.header.appname' | translate}}</a> <a href="#/user" class="item" translate="views.header.user"
				ng-show="hasRole('ROLE_ADMIN')"></a> <a href="#/centre" class="item" translate="views.header.centre"></a> <a href="#/transaction" class="item" translate="views.header.transaction"
				ng-show="hasRole('ROLE_USER')"></a>
			<div class="right menu">
				<div class="ui simple dropdown item">
					{{ 'LOCALE_LANGUAGE' | translate }} <i class="dropdown icon"></i>
					<div class="menu">
						<a class="item" href="" ng-click="changeLanguage('en')" translate="LOCALE_LANG_EN"></a> <a class="item" href="" ng-click="changeLanguage('zh')" translate="LOCALE_LANG_ZH"></a>
					</div>
				</div>
				<a href="logout" class="ui item"><i class="sign out icon"></i>{{'views.header.logout' | translate}}</a>
			</div>
		</div>
	</div>

	<div ng-view></div>


	<div class="ui basic modal" id="deleteConfirmModal">
		<div class="ui icon header">
			<i class="remove circle icon"></i> {{ 'modal.delete.confirm' | translate }}
		</div>
		<div class="actions">
			<div class="ui red basic cancel inverted button">
				<i class="remove icon"></i> {{ 'modal.delete.no' | translate }}
			</div>
			<div class="ui green ok inverted button">
				<i class="checkmark icon"></i> {{ 'modal.delete.yes' | translate }}
			</div>
		</div>
	</div>
	
	<script src="webjars/jquery/3.1.1/jquery.min.js"></script>
	<script src="webjars/Semantic-UI/2.2.10/semantic.min.js"></script>
	<script src="webjars/angularjs/1.5.6/angular.min.js"></script>
	<script src="webjars/angular-route/1.5.6/angular-route.min.js"></script>
	<script src="webjars/angular-ui-grid/4.0.6/ui-grid.min.js"></script>
	<script src="webjars/angular-translate/2.13.1/angular-translate.min.js"></script>
	<script src="webjars/angular-translate-loader-url/2.13.1/angular-translate-loader-url.min.js"></script>
	<script src="webjars/angular-translate-loader-static-files/2.13.1/angular-translate-loader-static-files.min.js"></script>

	<script src="resources/scripts/angular/app.js"></script>
	<script src="resources/scripts/angular/controllers/appCtrl.js"></script>
	<script src="resources/scripts/angular/controllers/components/centres.js"></script>
	<script src="resources/scripts/angular/controllers/components/users.js"></script>
	<script src="resources/scripts/angular/controllers/components/transactions.js"></script>
</body>
</html>
