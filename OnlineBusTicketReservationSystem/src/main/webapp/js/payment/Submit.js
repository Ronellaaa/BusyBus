
  // Ensure DOM is fully loaded
  document.addEventListener('DOMContentLoaded', function() {
    const termsCheckbox = document.getElementById('terms-checkbox');
    const payButton = document.getElementById('pay-button');
    const expiryInput = document.getElementById('expiry');

	//Make sure submission button is enabled once checkbox is checked.
    if (termsCheckbox && payButton) {
      termsCheckbox.addEventListener('change', function() {
        payButton.disabled = !this.checked;
      });
    }

	//Make sure the user is unable to select a date before the current date as the expiry date.
    if (expiryInput) {
      expiryInput.min = new Date().toISOString().split('T')[0];
    }
  });
