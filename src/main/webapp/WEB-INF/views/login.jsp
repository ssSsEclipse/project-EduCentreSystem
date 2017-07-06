<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="webjars/Semantic-UI/2.2.10/semantic.min.css" charset="UTF-8">
<link rel="stylesheet" type="text/css" href="resources/styles/login.css">
<script src="webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="webjars/Semantic-UI/2.2.10/semantic.min.js"></script>
<script>
	$(document).ready(function() {
		$('.ui.form').form({
			fields : {
				username : {
					identifier : 'username',
					rules : [ {
						type : 'empty',
						prompt : 'Please enter your username'
					} ]
				},
				password : {
					identifier : 'password',
					rules : [ {
						type : 'empty',
						prompt : 'Please enter your password'
					} ]
				}
			}
		});
	});
</script>
</head>
<body>

	<div class="ui middle aligned center aligned grid">
		<div class="column">
			<h2 class="ui teal header">
				<div class="content">Portal</div>
			</h2>
			<form class="ui large form" name='loginForm' action="login" method='POST'>
				<div class="ui stacked segment">

					<div class="field">
						<div class="ui left icon input">
							<i class="user icon"></i> <input type="text" name="username" placeholder="Username">
						</div>
					</div>
					<div class="field">
						<div class="ui left icon input">
							<i class="lock icon"></i> <input type="password" name="password" placeholder="Password">
						</div>
					</div>
					<div class="ui fluid large teal submit button">Login</div>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />


				<div class="ui error message"></div>
			</form>
			<c:if test="${not empty error}">
				<div class="ui error message">
					<ul class="list">
						<li>${error}</li>
					</ul>
				</div>
			</c:if>
		</div>
	</div>

</body>
</html>