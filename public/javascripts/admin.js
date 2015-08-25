//story10: Administrator login verification
$('.ui.form').form({
  adminEmail : {
		identifier : 'adminEmail',
		rules : [ {
			type : 'isExactly[admin]',
			prompt : 'Please enter correct Administrator name and/or Password'
		} ]
	},
	//for password field only checking if it isn't empty:
	adminPassword : {
		identifier : 'adminPassword',
		rules : [ {
			type : 'empty',
			prompt : 'Please enter correct Password'
		}]
	}
});