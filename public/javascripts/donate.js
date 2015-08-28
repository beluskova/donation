$('.ui.selection.dropdown').dropdown();

$('.ui.form').form({
	candidateEmail : {
		identifier : 'candidateEmail',
		rules : [ {
			type : 'empty',
			prompt : 'Please select a Candidate to whom you wish to donate'
		} ]
	},
	amountDonated : {
		identifier : 'amountDonated',
		rules : [ {
			type : 'empty',
			prompt : 'Please select an amount to donate'
		} ]
	}
},
{
  onSuccess : function() {
      submitForm();
      return false;
  } 
});

//story14: avoid page flicker
function submitForm() {
var formData = $('.ui.form input').serialize(); 
$.ajax({
  type: 'POST',
  url: '/donation/donate',
  data: formData,
  success: function(response) { 
     $('#notification').text("Thank you for your donation");
  }
});
$('.form').trigger("reset");
}


$('.jumping.picture')
.transition('swing up', '2000ms')
.transition('swing down','2000ms')
.transition('fade up', '2000ms')
.transition('fade in', '2000ms');