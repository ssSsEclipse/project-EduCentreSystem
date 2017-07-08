var app = angular.module('app', [ 'ui.grid', 'ui.grid.pagination',
		'ui.grid.resizeColumns' ]);

app.factory('CommonFactory', function() {
	return {
		resetForm : function(form) {
			$(form).form('reset');
		},
		buildResponseMessage: function(messageDiv, message, type) {
			if ($(messageDiv).transition('is visible')){
				$(messageDiv).transition('hide');
			}
			$(messageDiv).html(message).removeClass('error success').addClass(type).transition('slide down');
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
						$(element)
						  .dropdown('set selected', user[name])
					} else {
						element.value = user[name];
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
					} else {
						obj[name] = value;
					}
				}
			}
			return JSON.stringify(obj);
		}
	}
});