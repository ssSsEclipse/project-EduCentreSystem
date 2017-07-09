var app = angular.module('app');

app.controller('AppCtrl', ['$translate', '$scope', function ($translate, $scope) {
 
  $scope.changeLanguage = function (langKey) {
    $translate.use(langKey);
  };
}]);