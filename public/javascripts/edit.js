//story03: verifying that user has replaced the original data with the new ones
$('.ui.form').form({
  firstName : {
		identifier : 'firstName',
		rules : [ {
			type : 'empty',
			prompt : 'Please enter your first name'
		} ]
	},
	age : {
		identifier : 'age',
		rules : [ {
			type : 'integer[0..110]',
			prompt : 'Please enter a valid age'
		} ]
	}
});
