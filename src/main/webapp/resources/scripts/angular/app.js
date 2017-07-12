var app = angular.module('app', [ 'ui.grid', 'ui.grid.pagination',
		'ui.grid.resizeColumns', 'pascalprecht.translate', 'ngRoute' ]);


app.factory('CommonFactory', function() {
	return {
		confirmDelete : function(modal, deleteCallback, args) {
			$(modal)
			    .modal({
			      closable  : false,
			      onDeny    : function(){
			        
			      },
			      onApprove : function() {
					deleteCallback.apply(this, args);
			      }
			    })
			    .modal('show')
			  ;
			  
		},
		hasRole : function(user, role) {
			  var result = false;
			  if (user) {
				  angular.forEach(user.authorities, function(item) {
					  if (item.authority && item.authority === role) {
						  result = true;
					  }
				  });
			  }
			  return result;
		},
		resetForm : function(form) {
			$(form).form('reset');
		},
		buildResponseMessage: function(messageDiv, message, type) {
			if ($(messageDiv).transition('is visible')){
				$(messageDiv).transition('hide');
			}
			$(messageDiv).html(message).removeClass('error success').addClass(type).transition('slide down');
			$("html, body").animate({ scrollTop: 0 }, "fast");
		},
		resetResponseMessage: function(messageDiv) {
			$(messageDiv).html('').removeClass('error success').transition('hide');
		},
		putDataToForm : function(user, form) {
			if (form.length > 0) {
				form = form[0];
			}
			var elements = form.querySelectorAll("input, select, textarea");
			for (var i = 0; i < elements.length; ++i) {
				var element = elements[i];
				var name = element.name;
				var type = element.type;
				
				if (user.hasOwnProperty(name)) {
					if (type == 'checkbox') {
						var parent = $($($(element)).parent());
						if (parent.hasClass('ui') && parent.hasClass('checkbox')) {
							parent.checkbox(user[name] ? 'check' : 'uncheck');
						}else {
							element.checked = user[name];
						}
					}else if (type == 'select-one') {
						if (null === user[name]) {
							$(element)
							  .dropdown('restore defaults');
						}else {
							$(element)
							  .dropdown('set selected', user[name]);
						}
					} else if (type == 'file') {
						console.log(user[name]);
					} else {
						element.value = user[name];
					}
				}else if (name === 'tutorialCentreId') { //special handling for tutorialCentre
					if (type == 'select-one') {
						if (null === user['tutorialCentre']) {
							$(element)
							  .dropdown('restore defaults');
						}else {
							$(element)
							  .dropdown('set selected', user['tutorialCentre']['id']);
						}
					}
				}
			}
		},
		formDataToJSONString : function(form) {
			var obj = {};
			if (form.length > 0) {
				form = form[0];
			}
			var elements = form.querySelectorAll("input, select, textarea");
			for (var i = 0; i < elements.length; ++i) {
				var element = elements[i];
				var name = element.name;
				var value = element.value;
				var type = element.type;

				if (name) {
					if (type == 'checkbox') {
						obj[name] = element.checked;
					}else if (type == 'file') {
						//obj[name] = element[0];
					}else {
						obj[name] = value;
					}
				}
			}
			return JSON.stringify(obj);
		}
	}
});

app.config(['$translateProvider', '$routeProvider', function ($translateProvider, $routeProvider) {
    $translateProvider.useStaticFilesLoader({
      prefix: 'resources/locales/locale-',
      suffix: '.json'
    });

    $translateProvider.preferredLanguage('zh');
    
	$routeProvider
		.when('/user', {
			templateUrl : 'resources/views/users.html',
			controller  : 'UsersCtrl',
			roles : ['ROLE_ADMIN']
		})
		.when('/centre', {
			templateUrl : 'resources/views/centres.html',
			controller  : 'CentresCtrl',
			roles : ['ROLE_ADMIN','ROLE_USER']
		})
		.when('/transaction', {
			templateUrl : 'resources/views/transactions.html',
			controller  : 'TransactionsCtrl',
			roles : ['ROLE_USER']
		})
		;
  }]);