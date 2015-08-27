//story11: Creating a new office

$('.ui.form').form({
	candidateFirstName : {
		identifier : 'candidate.candidateFirstName',
		rules : [ {
			type : 'empty',
			prompt : 'Please enter first name'
		} ]
	},
	candidateEmail : {
		identifier : 'candidate.candidateEmail',
		rules : [ {
			type : 'email',
			prompt : 'Please enter correct Email address'
		} ]
	},
	candidatePassword : {
		identifier : 'candidate.candidatePassword',
		rules : [ {
			type : 'empty',
			prompt : 'Please enter a password for candidate'
		}, {
			type : 'length[6]',
			prompt : 'Your password must be at least 6 characters'
		} ]
	},
});

$('.ui.selection.dropdown').dropdown();