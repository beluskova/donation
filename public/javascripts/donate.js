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

$('.homer.picture')
.transition('fade up', '2000ms')
.transition('fade in', '2000ms')
.transition('swing left')
.transition('swing right')
;