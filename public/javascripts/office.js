//story11: Creating a new office
$('.ui.form').form({
	title : {
		identifier : 'office.title',
		rules : [ {
			type : 'empty',
			prompt : 'Please enter office tittle'
		} ]
	},
	phone : {
		identifier : 'office.phone',
		rules : [ {
			type : 'empty',
			prompt : 'Please enter phone number'
		} 
		]
	}
},
{
  inline: true,
  on: 'blur',
  transition: 'fade down',
  }
});

