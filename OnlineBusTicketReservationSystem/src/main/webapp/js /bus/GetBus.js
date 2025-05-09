console.log("GetBus.js script loaded");

document.addEventListener("DOMContentLoaded", function () {
    console.log("JS loaded and DOM ready!");

    const totalSeatsInput = document.getElementById("total_seats");
    const seatsAvailableInput = document.getElementById("seats_available");

    if (!totalSeatsInput || !seatsAvailableInput) {
        console.error("Input fields not found.");
        return;
    }

    function validateSeats() {
        const totalSeats = parseInt(totalSeatsInput.value || 0, 10);
        const seatsAvailable = parseInt(seatsAvailableInput.value || 0, 10);

        if (seatsAvailable > totalSeats) {
            alert("Seats Available cannot be greater than Total Seats.");
            seatsAvailableInput.value = totalSeats;
        }
    }

    seatsAvailableInput.addEventListener("input", validateSeats);
    totalSeatsInput.addEventListener("input", validateSeats);
});
