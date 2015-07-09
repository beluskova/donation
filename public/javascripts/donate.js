$('.ui.dropdown').dropdown();

$('.ui.form')
.form({
  amountDonated: {
    identifier: 'amountDonated',
    rules: [{
        type: 'empty',
        prompt: 'Please select an amount to donate'
      }]
  }
});

$('#progress').progress('increment','${donationprogress}');

$('.jumping.picture')
.transition('swing up', '2000ms')
.transition('swing down', '2000ms')
.transition('fade up', '2000ms')
.transition('fade in', '2000ms')
;