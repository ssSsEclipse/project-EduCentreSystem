<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<link rel="stylesheet" type="text/css" href="resources/styles/home.css">
<div class="ui fixed inverted menu">
	<div class="ui container">
		<a href="home" class="header item"> Education Centre System </a>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a href="users" class="item">Users</a>
			<a href="centres" class="item">Centres</a>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_USER')">
			<a href="transactions" class="item">Transactions</a>
		</sec:authorize>
		<a href="logout" class="ui floated right item">Logout</a>
	</div>
</div>
