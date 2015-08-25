$('.ui.dropdown').dropdown();

$('.ui.form')
.form({
	candidateEmail: {
    identifier: 'candidateEmail',
    rules: [{
        type: 'empty',
        prompt: 'Please select a Candidate to whom you wish to donate'
      }]
  },
amountDonated: {
  identifier: 'amountDonated',
  rules: [{
      type: 'empty',
      prompt: 'Please select an amount to donate'
    }]
}
});

$('.jumping.picture')
.transition('swing up', '2000ms')
.transition('swing down', '2000ms')
.transition('fade up', '2000ms')
.transition('fade in', '2000ms')
;