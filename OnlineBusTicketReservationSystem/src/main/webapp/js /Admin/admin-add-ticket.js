document.getElementById("bus-name").addEventListener("change", updateFields);
document.getElementById("bus-type").addEventListener("change", updateFields);

function updateFields() {
  const busName = document.getElementById("bus-name").value;
  const busType = document.getElementById("bus-type").value;
  const totalSeats = document.getElementById("total-seats");
  const price = document.getElementById("price");


    const matchedBus = buses.find(bus => bus.busName === busName && bus.type === busType);

    if (matchedBus) {
      totalSeats.value = matchedBus.totalSeats;
      price.value = matchedBus.price;
    } else {
      totalSeats.value = "";
      price.value = "";
    }
  
}
