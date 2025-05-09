const seatLayout = document.getElementById("seatLayout");
const selectedSeatsText = document.getElementById("selectedSeatsText");
const  totalPrice = document.getElementById("totalPrice");

let pricePerSeat = 1000;
let selectedCount = 0;
let selectedSeats = [];

for(let i = 1; i <= 53;){
	const rowDiv = document.createElement("div");
	rowDiv.className = "seat-row";
	
	if(i>=49){
		for(let j = 0; j <=5 ; j++,i++){
			createSeat(rowDiv,i);
		}
	}else{
		for(let j =0; j< 2;j++,i++){
			createSeat(rowDiv,i);
		}
		
		const gap = document.createElement("div");
		gap.className ="seat-gap";
		rowDiv.appendChild(gap);
		
		for(let j =0 ; j < 3;j++,i++){
			createSeat(rowDiv,i)
		}
		
	}
	
	seatLayout.appendChild(rowDiv);
}

function createSeat(row,number){
	const seat = document.createElement("button");
	seat.className ="seat";
	seat.innerText =number;
	seat.setAttribute("data-seat", number);
	const isBooked = bookedSeats.includes(`${number}`);


	 if (isBooked) {
	     seat.disabled = true;
	     seat.style.background = "#ccc"; // grey for already booked
	 }
	
	seat.addEventListener("click",()=>{
		if(!selectedSeats.includes(number)){
			const gender = prompt(`Select gender for seat ${number} (M/F):`);
			if(gender === "M" || gender === "F" || gender === "m" || gender === "f" ){
				selectedSeats.push(`${number}-${gender}`);
				selectedCount++;
				seat.disabled =true;
				seat.style.background = "#999";
				updateDetails();
				
			}else{
				alert("Please enter M or F");
			}
		}
	});
	row.appendChild(seat);
}


function updateDetails(){
	selectedSeatsText.innerText = selectedSeats.join(" , ");
	totalPrice.innerText = selectedCount * pricePerSeat + "LKR";
}