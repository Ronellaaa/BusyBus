// Wait for the entire DOM to be loaded before running the script
document.addEventListener("DOMContentLoaded", function () {
  console.log("JS loaded and DOM ready!");

  // Get references to the Total Seats and Seats Available input fields
  const totalSeatsInput = document.getElementById("total_seats");
  const seatsAvailableInput = document.getElementById("seats_available");

  // If either input is not found, log an error and stop the script
  if (!totalSeatsInput || !seatsAvailableInput) {
    console.error("Input fields not found.");
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
});
