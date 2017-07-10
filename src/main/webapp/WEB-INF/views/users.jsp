<!doctype html>
<html ng-app="app">
<head>
<title>{{'views.header.user' | translate}}</title>
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
<script src="resources/scripts/angular/controllers/components/users.js"></script>
<style>
.grid {
	height: 400px;
}
</style>
</head>
<body ng-controller="AppCtrl">
	<jsp:include page="component/header.jsp" />

	<div class="ui main container" ng-controller="UsersCtrl">
		<h1 class="ui dividing header">{{'views.header.user' | translate}}</h1>

		<%-- Form --%>
		<div class="ui attached message" ng-show="editMode">
			<p>{{'views.user.editing' | translate}} {{editingUser}}</p>
		</div>
		<form class="ui form attached fluid segment" id="userForm">
			<div id="responseMessage" class="ui message hidden"></div>
			<div class="ui error message"></div>
			<input type="hidden" name="id">
			<div class="two fields">
				<div class="field">
					<label>{{'views.user.username' | translate}}</label> <input type="text" placeholder="{{'views.user.username' | translate}}" name="username">
				</div>
				<div class="field">
					<label>{{'views.user.password' | translate}}</label> <input type="password" placeholder="{{'views.user.password.placeholder' | translate}}" name="password">
				</div>
			</div>
			<div class="three fields">
				<div class="field">
					<label>{{'views.user.role' | translate}}</label> 
					<select class="ui fluid selection dropdown" name="role" id="roleDropdown">
						<option value=""></option>
						<option value="ROLE_ADMIN">Admin</option>
						<option value="ROLE_USER">User</option>
					</select>
				</div>
				<div class="field">
					<label>{{'views.user.tutorialCentre' | translate}}</label> 
					<select class="ui search selection dropdown" name="tutorialCentreId" id="tutorialCentreId">
						<option value=""></option>
					</select>
				</div>
				<div class="field">
					<label>{{'views.user.active' | translate}}</label>
					<div class="ui toggle checkbox" id="activeCheckboxDiv">
						<input type="checkbox" name="active" id="activeCheckbox" /><label for="activeCheckbox" id="activeLabel">{{'views.user.active.false' | translate}}</label>
					</div>
				</div>
			</div>
			<button class="ui button" ng-click="submitForm()" ng-class="editMode ? 'green':'primary'">
				<div ng-if="!editMode">
					<i class="add user icon"></i>{{'button.create' | translate}}
				</div>
				<div ng-if="editMode">
					<i class="edit icon"></i>{{'button.update' | translate}}
				</div>
			</button>
			<button class="ui reset button" ng-click="editMode = false">
				<i class="erase icon"></i>{{'button.reset' | translate}}
			</button>

		</form>

		<h1 class="ui dividing header">{{'views.subheader.list' | translate}}</h1>
		<div>
			<div ui-grid="gridOptions" external-scopes="$scope" ui-grid-resize-columns ui-grid-pagination class="grid"></div>
		</div>
	</div>

</body>
</html>
