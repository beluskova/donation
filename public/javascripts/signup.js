$('.ui.form').form({
	firstName : {
		identifier : 'user.firstName',
		rules : [ {
			type : 'empty',
			prompt : 'Please enter your name'
		} ]
	},
	// story01: age can be only valid integer between 0 and 110
	age : {
		identifier : 'user.age',
		rules : [ {
			type : 'integer[0..110]',
			prompt : 'Please enter a valid age'
		} ]
	},
	//
	email : {
		identifier : 'user.email',
		rules : [ {
			type : 'email',
			prompt : 'Please enter a valid email address'
		} ]
	},
	password : {
		identifier : 'user.password',
		rules : [ {
			type : 'empty',
			prompt : 'Please enter a password'
		}, {
			type : 'length[6]',
			prompt : 'Your password must be at least 6 characters'
		} ]
	}
}, {
	inline : true,
	on : 'blur',
	transition : 'fade down',
});

$('.ui.checkbox').checkbox();

//story01: the State selection dropdown is enabled only for usCitizens (when checkbox "usCitizen" on top is checked)
var $checkBox = $('#usCitizen'), $select = $('#state');

$checkBox.on('change', function(e) {
	if ($(this).is(':checked')) {
		$select.removeAttr('disabled');
	} else {
		$select.attr('disabled', 'disabled');
	}
});

//story06
$('.ui.selection.dropdown').dropdown();
