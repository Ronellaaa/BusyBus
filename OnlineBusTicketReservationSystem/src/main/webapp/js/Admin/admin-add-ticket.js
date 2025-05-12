
//Toggle side-bar function
const toggleBtn = document.getElementById("toggle-menu-btn");
 const sidebar = document.querySelector(".admin-sidebar");
 const main = document.querySelector("main");

 toggleBtn.addEventListener("click", () => {
   sidebar.classList.toggle("collapsed");

   // Optional: dynamically adjust main area
   if (sidebar.classList.contains("collapsed")) {
     document.body.style.gridTemplateColumns = "80px 1fr";
   } else {
     document.body.style.gridTemplateColumns = "250px 1fr";
   }
 });
 
 
//search fuction 

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
