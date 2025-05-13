
window.addEventListener("DOMContentLoaded", () => {
  const seatLayout = document.getElementById("seatLayout");
  const selectedSeatsText = document.getElementById("selectedSeatsText");
  const totalPriceDisplay = document.getElementById("totalPrice");
  const seatInput = document.getElementById("seatInput");
  const priceInput = document.getElementById("priceInput");

  const cardSeats = document.getElementById("cardSeats");
  const cardPrice = document.getElementById("cardPrice");

  const pricePerSeat = typeof window.pricePerSeat !== "undefined" ? parseFloat(window.pricePerSeat) : 1000;

  const totalSeats = typeof window.totalSeats !== "undefined" ? parseInt(window.totalSeats) : 50;

  let selectedSeats = [];

  // === Generate All Seats ===
  for (let i = 1; i <= totalSeats;) {
    const rowDiv = document.createElement("div");
    rowDiv.className = "seat-row";

    if (i >= totalSeats - 5) {
      for (let j = 0; j < 6 && i <= totalSeats; j++, i++) {
        createSeat(rowDiv, i);
      }
    } else {
      for (let j = 0; j < 2 && i <= totalSeats; j++, i++) {
        createSeat(rowDiv, i);
      }

      const gap = document.createElement("div");
      gap.className = "seat-gap";
      rowDiv.appendChild(gap);

      for (let j = 0; j < 3 && i <= totalSeats; j++, i++) {
        createSeat(rowDiv, i);
      }
    }

    seatLayout.appendChild(rowDiv);
  }

  // === Function to Create a Seat ===
  function createSeat(row, number) {
    const seat = document.createElement("button");
    seat.className = "seat";
    seat.innerText = number;
    seat.setAttribute("data-seat", number);

	const isBooked = bookedSeats.includes(String(number));


    if (isBooked) {
      seat.disabled = true;
      seat.classList.add("booked-seat"); // use CSS class to color gray
    }
	seat.addEventListener("click", () => {
	  if (!isBooked && !selectedSeats.some(s => s.startsWith(number + "-"))) {
	    const gender = prompt(`Select gender for seat ${number} (M/F):`);
	   
	    if (gender && (gender.toUpperCase() === "M" || gender.toUpperCase() === "F") ) {
	      selectedSeats.push(`${number}-${gender.toUpperCase()}}`);
	      seat.disabled = true;
	      seat.classList.add("selected-seat");
	      updateDetails();
	    } else {
	      alert("Enter valid gender (M/F)");
	    }
	  }
	});

    row.appendChild(seat);
  }

  // === Update Booking Info ===
  function updateDetails() {
    const seatNumbersOnly = selectedSeats.map(s => s.split("-")[0]);
    const total = seatNumbersOnly.length * pricePerSeat;

    selectedSeatsText.innerText = seatNumbersOnly.join(", ") || "None";
    totalPriceDisplay.innerText = total + " LKR";
    seatInput.value = selectedSeats.join(",");
    priceInput.value = total;
	
	const seatCountInput = document.getElementById("seatCountInput");
	  seatCountInput.value = seatNumbersOnly.length;

	document.getElementById("seatTypeInput").value = seatTypeLabel;
	
    if (cardSeats) cardSeats.innerText = "Seats: " + seatNumbersOnly.join(", ");
    if (cardPrice) cardPrice.innerText = "Price: LKR " + total.toFixed(2);
  }
});

