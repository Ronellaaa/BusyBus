document.addEventListener("DOMContentLoaded", function () {

  const form = document.querySelector("form"); 

  // Get references to the Total Seats and Seats Available input fields
  const totalSeatsInput = document.getElementById("total_seats");
  const seatsAvailableInput = document.getElementById("seats_available");

  // If any input is not found, log an error and stop the script
  if (!totalSeatsInput || !seatsAvailableInput || !form) { 
    console.error("Input fields or form not found.");
    return;
  }

  // Function to validate that Seats Available is not greater than Total Seats
  function validateSeats() {
    const totalSeats = parseInt(totalSeatsInput.value || 0, 10);
    const seatsAvailable = parseInt(seatsAvailableInput.value || 0, 10);

    // If invalid, show alert and set seatsAvailable to max of totalSeats
    if (seatsAvailable > totalSeats) {
      alert("Seats Available cannot be greater than Total Seats.");
      seatsAvailableInput.value = totalSeats;
    }
  }

  // Attach event listeners to validate on input change
  seatsAvailableInput.addEventListener("input", validateSeats);
  totalSeatsInput.addEventListener("input", validateSeats);

  //--------------------------------------------------------------------------

  const busNameInput = document.getElementById("bus_name");
  busNameInput.addEventListener("input", function() {
    if (busNameInput.value.length < 3) {
      busNameInput.setCustomValidity("Bus Name should be at least 3 characters.");
      busNameInput.classList.add("invalid"); // Add an invalid class for visual feedback
    } else {
      busNameInput.setCustomValidity("");
      busNameInput.classList.remove("invalid"); // Remove invalid class when valid
    }
  });

  //--------------------------------------------------------------------------

  form.addEventListener("submit", function(e) {
    if (!busNameInput.checkValidity()) {
      e.preventDefault(); // Prevent form submission if bus name is invalid
    }
  });

});
