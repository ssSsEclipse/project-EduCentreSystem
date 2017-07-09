<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<link rel="stylesheet" type="text/css" href="resources/styles/header.css">

<sec:authentication property="principal.id" var="userId"  />
<div class="ui fixed inverted menu">
	<div class="ui container">
		<a href="home" class="header item" translate="views.header.appname"></a>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a href="users" class="item" translate="views.header.user"></a>
			<a href="centres" class="item" translate="views.header.centre"></a>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_USER')">
			<a href="centres/edit?${userId}" class="item" translate="views.header.centre"></a>
			<a href="transactions" class="item" translate="views.header.transaction"></a>
		</sec:authorize>
		<div class="right menu">
			<div class="ui simple dropdown item">
				{{ 'LOCALE_LANGUAGE' | translate }} <i class="dropdown icon"></i>
				<div class="menu">
					<a class="item" href="#" ng-click="changeLanguage('en')" translate="LOCALE_LANG_EN"></a> <a class="item" href="#" ng-click="changeLanguage('zh')" translate="LOCALE_LANG_ZH"></a>
				</div>
			</div>
			<a href="logout" class="ui item" translate="views.header.logout"></a>
		</div>
	</div>
</div>
