console.log("GetBus.js script loaded");

document.addEventListener("DOMContentLoaded", function () {
    console.log("JS loaded and DOM ready!");

	const form = document.querySelector('form');  // Ensure to select the form element

	    if (!form) {
	        console.error("Form not found.");
	        return;
	    }

	    // Get references to the input fields
	    const totalSeatsInput = document.getElementById("total_seats");
	    const seatsAvailableInput = document.getElementById("seats_available");

	    if (!totalSeatsInput || !seatsAvailableInput) {
	        console.error("Input fields not found.");
	        return;
	    }

	    // Function to validate that seats available is not greater than total seats
	    function validateSeats() {
	        const totalSeats = parseInt(totalSeatsInput.value || 0, 10);
	        const seatsAvailable = parseInt(seatsAvailableInput.value || 0, 10);

	        if (seatsAvailable > totalSeats) {
	            alert("Seats Available cannot be greater than Total Seats.");
	            seatsAvailableInput.value = totalSeats;
	        }
	    }

	    // Attach event listeners to validate on input change
	    seatsAvailableInput.addEventListener("input", validateSeats);
	    totalSeatsInput.addEventListener("input", validateSeats);

	    // Get bus name input field and add validation for minimum length
	    const busNameInput = document.getElementById("bus_name");
	    busNameInput.addEventListener("input", function() {
	        if (busNameInput.value.length < 3) {
	            busNameInput.setCustomValidity("Bus Name should be at least 3 characters.");
	            busNameInput.classList.add("invalid"); // Add invalid class for visual feedback
	        } else {
	            busNameInput.setCustomValidity("");
	            busNameInput.classList.remove("invalid"); // Remove invalid class when valid
	        }
	    });

	    // Attach submit event listener to form to prevent submission if bus name is invalid
	    form.addEventListener("submit", function(e) {
	        if (!busNameInput.checkValidity()) {
	            e.preventDefault(); // Prevent form submission if bus name is invalid
	        }
	    });

});
