<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<html ng-app="app">
<head>
<title>{{"views.home.welcome" | translate}}</title>
<link rel="stylesheet" type="text/css" href="webjars/Semantic-UI/2.2.10/semantic.min.css" charset="UTF-8">
<script src="webjars/jquery/3.1.1/jquery.min.js"></script>
<script src="webjars/Semantic-UI/2.2.10/semantic.min.js"></script>

<script src="webjars/angularjs/1.5.6/angular.min.js"></script>
<script src="webjars/angular-ui-grid/4.0.6/ui-grid.min.js"></script>

<script src="webjars/angular-translate/2.13.1/angular-translate.min.js"></script>
<script src="webjars/angular-translate-loader-url/2.13.1/angular-translate-loader-url.min.js"></script>
<script src="webjars/angular-translate-loader-static-files/2.13.1/angular-translate-loader-static-files.min.js"></script>

<script src="resources/scripts/angular/app.js"></script>
<script src="resources/scripts/angular/controllers/appCtrl.js"></script>

</head>
<body ng-controller="AppCtrl">
  <jsp:include page="component/header.jsp" />

  <div class="ui main text container">
    <h1 class="ui header">{{"views.home.welcome" | translate}}</h1>
  </div>
</body>
</html>
