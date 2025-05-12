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
function searchRoute(){
	var input = document.getElementById("search-bar");
	var filter = input.value.toLowerCase();
	var table = document.getElementById("routes-table");
	var tbody = table.getElementsByTagName("tbody")[0];
	var tr = tbody.getElementsByTagName("tr");
	
	
	for (i = 0; i < tr.length; i++) {
		var tdArray = tr[i].getElementsByTagName("td");
		var rowContainsFilter = false;
		
		for (var j = 0; j < tdArray.length; j++) {
			if (tdArray[j]) {
				var textValue = tdArray[j].textContent || tdArray[j].innerText;
				
				if(textValue.toLowerCase().indexOf(filter) > -1){
					rowContainsFilter = true;
					         break;
					
				    }
				}
		   }
		   tr[i].style.display = rowContainsFilter ? "" : "none";
	  }
	    
}

function searchCustomer(){
	var input = document.getElementById("search-bar");
		var filter = input.value.toLowerCase();
		var table = document.getElementById("cus-table")||document.getElementById("payment-table")||document.getElementById("booking-table");
		var tbody = table.getElementsByTagName("tbody")[0];
		var tr = tbody.getElementsByTagName("tr");
		
		for(var i=0;tr.length;i++){
			var tdArray = tr[i].getElementsByTagName("td");
			var rowContainsFilter = false;
			
			for(var j=0;j<tdArray.length;j++){
				if(tdArray[j]){
					var textValue = tdArray[j].textContent ||  tdArray[j].innerText;
					
					if(textValue.toLowerCase().indexOf(filter) > -1){
						rowContainsFilter = true;
						break;
					}
				}
			}
			tr[i].style.display = rowContainsFilter ? "" : "none";
			
		}
}

function searchBusOperator(){
	var input = document.getElementById("search-bar");
		var filter = input.value.toLowerCase();
		var table = document.getElementById("bus-table");
		var tbody = table.getElementsByTagName("tbody")[0];
		var tr = tbody.getElementsByTagName("tr");
		
		for(var i=0;tr.length;i++){
			var tdArray = tr[i].getElementsByTagName("td");
			var rowContainsFilter = false;
			
			for(var j=0;j<tdArray.length;j++){
				if(tdArray[j]){
					var textValue = tdArray[j].textContent ||  tdArray[j].innerText;
					
					if(textValue.toLowerCase().indexOf(filter) > -1){
						rowContainsFilter = true;
						break;
					}
				}
			}
			tr[i].style.display = rowContainsFilter ? "" : "none";
			
		}
}

