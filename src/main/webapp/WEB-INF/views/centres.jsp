<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!doctype html>
<html ng-app="app">
<head>
<title>{{'views.header.centre' | translate}}</title>
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
<script src="resources/scripts/angular/controllers/components/centres.js"></script>
<style>
.grid {
	height: 400px;
}
</style>
</head>
<body ng-controller="AppCtrl">
	<jsp:include page="component/header.jsp" />

	<div class="ui main container" ng-controller="CentresCtrl">
		<h1 class="ui dividing header">{{'views.header.centre' | translate}}</h1>

		<%-- Form --%>
		<div class="ui attached message" ng-show="editMode">
			<p>{{'views.centre.editing' | translate}} {{editingCentre}}</p>
		</div>
		<form class="ui form attached fluid segment" id="centreForm">
			<div id="responseMessage" class="ui message hidden"></div>
			<div class="ui error message"></div>
			<input type="hidden" name="id"> <input type="hidden" name="createDateTime">
			<h4 class="ui dividing header">{{'views.centre.section.centre.info' | translate}}</h4>
			<div class="field">
				<label>{{'views.centre.schoolName' | translate}}</label> <input type="text" placeholder="{{'views.centre.schoolName' | translate}}" name="schoolName">
			</div>
			<div class="field">
				<label>{{'views.centre.schoolAddress' | translate}}</label> <input type="text" placeholder="{{'views.centre.schoolAddress' | translate}}" name="schoolAddress">
			</div>
			<div class="two fields">
				<div class="field">
					<label>{{'views.centre.schoolPhone' | translate}}</label> <input type="text" placeholder="{{'views.centre.schoolPhone' | translate}}" name="schoolPhone">
				</div>
				<div class="field">
					<label>{{'views.centre.schoolFax' | translate}}</label> <input type="text" placeholder="{{'views.centre.schoolFax' | translate}}" name="schoolFax">
				</div>
			</div>
			<div class="two fields">
				<div class="field">
					<label>{{'views.centre.email' | translate}}</label> <input type="email" placeholder="{{'views.centre.email' | translate}}" name="email">
				</div>
				<div class="field">
					<label>{{'views.centre.website' | translate}}</label> <input type="text" placeholder="{{'views.centre.website' | translate}}" name="website">
				</div>
			</div>
			<h4 class="ui dividing header">{{'views.centre.section.pic.info' | translate}}</h4>
			<div class="two fields">
				<div class="field">
					<label>{{'views.centre.institutionPic' | translate}}</label> <input type="text" placeholder="{{'views.centre.institutionPic.placeholder' | translate}}" name="institutionPic">
				</div>
				<div class="field">
					<label>{{'views.centre.picMobile' | translate}}</label> <input type="text" placeholder="{{'views.centre.picMobile.placeholder' | translate}}" name="picMobile">
				</div>
			</div>
			<h4 class="ui dividing header">{{'views.centre.section.bank.info' | translate}}</h4>
			<div class="three fields">
				<div class="field">
					<label>{{'views.centre.bankName' | translate}}</label> <input type="text" placeholder="{{'views.centre.bankName' | translate}}" name="bankName">
				</div>
				<div class="field">
					<label>{{'views.centre.accountName' | translate}}</label> <input type="text" placeholder="{{'views.centre.accountName' | translate}}" name="accountName">
				</div>
				<div class="field">
					<label>{{'views.centre.accountNumber' | translate}}</label> <input type="text" placeholder="{{'views.centre.accountNumber' | translate}}" name="accountNumber">
				</div>
			</div>
			<h4 class="ui dividing header">{{'views.centre.section.discount.info' | translate}}</h4>
			<div class="two fields">
				<div class="field">
					<label>{{'views.centre.couponCode' | translate}}</label>
					<div class="ui action input">
						<input type="text" placeholder="{{'views.centre.couponCode' | translate}}" name="couponCode" readonly="" id="couponCode">
						<button class="ui button" ng-click="generateCouponCode()"><i class="refresh icon"></i>{{'button.generate' | translate}}</button>
					</div>
				</div>
				<div class="field">
					<label>{{'views.centre.discountComissionPdf' | translate}}</label> 
					<div class="ui action input">
						<input type="text" placeholder="{{'views.centre.discountComissionPdf' | translate}}" name="discountComissionPdf" readonly="" id="discountComissionPdf" value="test.pdf">
						<button class="ui button" onclick="window.open('resources/pdf/'+$('#discountComissionPdf').val());"><i class="download icon"></i>{{'button.download' | translate}}</button>
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

		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<h1 class="ui dividing header">{{'views.subheader.list' | translate}}</h1>
			<div>
				<div ui-grid="gridOptions" external-scopes="$scope" ui-grid-pagination class="grid"></div>
			</div>
		</sec:authorize>
	</div>

</body>
</html>
