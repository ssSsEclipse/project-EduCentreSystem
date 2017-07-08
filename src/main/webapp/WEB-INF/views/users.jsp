<!doctype html>
<html ng-app="app">
<head>
<link rel="stylesheet" type="text/css" href="webjars/Semantic-UI/2.2.10/semantic.min.css" charset="UTF-8">
<script src="webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="webjars/Semantic-UI/2.2.10/semantic.min.js"></script>

<link rel="stylesheet" href="webjars/angular-ui-grid/4.0.6/ui-grid.min.css">
<script src="webjars/angularjs/1.5.6/angular.min.js"></script>
<script src="webjars/angular-ui-grid/4.0.6/ui-grid.min.js"></script>

<script src="resources/scripts/angular/app.js"></script>
<script src="resources/scripts/angular/component/users.js"></script>
<script type="text/javascript">
	$(function() {
		$('.ui.dropdown').dropdown();
		var formValidationRules = {
			fields : {
				username : {
					identifier : 'username',
					rules : [ {
						type : 'empty',
						prompt : 'Please enter username'
					} ]
				},
				password : {
					identifier : 'password',
					rules : [ {
						type : 'empty',
						prompt : 'Please enter password'
					} /* ,
					{
						type : 'regExp[/^\$.{2}\$.{2}\$.{53}$/]',
						prompt : 'Please enter a valid password encrypted in bycrypt'
					} */]
				}
			}
		};
		$('.ui.form').form(formValidationRules);
		$('#activeCheckboxDiv').checkbox( {
			onChange : function() {
				$('#activeLabel').html($('#activeCheckbox').prop('checked') ? 'Active' : 'Inactive');
			}
		});
	});
</script>
<style>
.grid {
	height: 400px;
}
</style>
</head>
<body>
	<jsp:include page="component/header.jsp" />

	<div class="ui main container" ng-controller="UsersCtrl">
		<h1 class="ui dividing header">User</h1>

		<%-- Form --%>
		<div id="responseMessage" class="ui message hidden"></div>
		<form class="ui form" id="userForm">
			<div class="ui error message"></div>
			<input type="hidden" name="id"> <input type="hidden" name="createDateTime">
			<div class="two fields">
				<div class="field">
					<label>Username</label> <input type="text" placeholder="Username" name="username">
				</div>
				<div class="field">
					<label>Password</label> <input type="password" placeholder="Encrypted Password (bcrpyt)" name="password">
				</div>
			</div>
			<div class="three fields">
				<div class="field">
					<label>Role</label> <select class="ui fluid search selection dropdown" name="role">
						<option value="">Select</option>
						<option value="ROLE_ADMIN">Admin</option>
						<option value="ROLE_USER">User</option>
					</select>
				</div>
				<div class="field">
					<label>Tutorial Centre Id</label> <input type="text" placeholder="Tutorial Centre Id" name="tutorialCentreId">
				</div>
				<div class="field">
					<label>Active</label>
					<div class="ui toggle checkbox" id="activeCheckboxDiv">
						<input type="checkbox" name="active" id="activeCheckbox"/><label for="activeCheckbox" id="activeLabel">Inactive</label>
					</div>
				</div>
			</div>
			<button class="ui primary button" ng-click="submitForm()">
				<div ng-if="!editMode">
					<i class="add user icon"></i>Create
				</div>
				<div ng-if="editMode">
					<i class="edit icon"></i>Update
				</div>
			</button>
			<button class="ui reset button" ng-click="editMode = false">
					<i class="erase icon"></i>Reset
			</button>

		</form>

		<h1 class="ui dividing header">List</h1>
		<div>
			<div ui-grid="gridOptions" external-scopes="$scope" ui-grid-resize-columns ui-grid-pagination class="grid"></div>
		</div>
	</div>

</body>
</html>
