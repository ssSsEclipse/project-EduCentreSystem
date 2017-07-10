<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<link rel="stylesheet" type="text/css" href="resources/styles/header.css">

<div class="ui fixed inverted menu">
	<div class="ui container">
		<a href="home" class="header item"><i class="circular inverted teal book icon"></i>{{'views.header.appname' | translate}}</a>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a href="users" class="item" translate="views.header.user"></a>
		</sec:authorize>
		<a href="centres" class="item" translate="views.header.centre"></a>
		<sec:authorize access="hasRole('ROLE_USER')">
			<a href="transactions" class="item" translate="views.header.transaction"></a>
		</sec:authorize>
		<div class="right menu">
			<div class="ui simple dropdown item">
				{{ 'LOCALE_LANGUAGE' | translate }} <i class="dropdown icon"></i>
				<div class="menu">
					<a class="item" href="#" ng-click="changeLanguage('en')" translate="LOCALE_LANG_EN"></a> <a class="item" href="#" ng-click="changeLanguage('zh')" translate="LOCALE_LANG_ZH"></a>
				</div>
			</div>
			<a href="logout" class="ui item"><i class="sign out icon"></i>{{'views.header.logout' | translate}}</a>
		</div>
	</div>
</div>
