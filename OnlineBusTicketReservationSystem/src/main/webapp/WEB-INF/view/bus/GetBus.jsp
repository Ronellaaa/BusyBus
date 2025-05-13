<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.bus.Bus"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Bus</title>
<link rel="stylesheet" href="styles/bus/GetBus.css" />
</head>

<body>


	<div class="busForm">

		<div class="container">
			<div class="title">Edit Bus Details</div>

			<%
			Bus bus = (Bus) request.getAttribute("bus");
			if (bus == null) {
			%>
			<div style="color: red; font-weight: bold;">No bus data found!</div>
			<%
			} else {
			%>

			<form method="POST" action="UpdateBusServlet">
				<input type="hidden" name="bus_id" value="<%=bus.getBusId()%>" />

				<div class="busDetails">

					<div class="input-box">
						<label for="bus_name" class="details">Bus Name:</label> <input
							type="text" class="input" id="bus_name" name="bus_name"
							value="<%=bus.getBusName()%>" required />
					</div>

					<div class="input-box">
						<label for="bus_type" class="details">Bus Type:</label> <select
							class="input" id="bus_type" name="bus_type" required>
							<option value="">-- Select Bus Type --</option>
							<option value="Luxury"
								<%=bus.getBusType().equals("Luxury") ? "selected" : ""%>>Luxury</option>
							<option value="Semi-Luxury"
								<%=bus.getBusType().equals("Semi-Luxury") ? "selected" : ""%>>Semi-Luxury</option>
							<option value="AC"
								<%=bus.getBusType().equals("AC") ? "selected" : ""%>>AC</option>
							<option value="Non-AC"
								<%=bus.getBusType().equals("Non-AC") ? "selected" : ""%>>Non-AC</option>
							<option value="Normal"
								<%=bus.getBusType().equals("Normal") ? "selected" : ""%>>Normal</option>
						</select>
					</div>

					<div class="input-box">
						<label for="price_per_seat" class="details">Price per Seat
							(LKR):</label> <input type="number" class="input" id="price_per_seat"
							name="price_per_seat" step="0.01" min="0"
							value="<%=bus.getPricePerSeat()%>" required />
					</div>

					<div class="input-box">
						<label for="total_seats" class="details">Total Seats:</label> <input
							type="number" class="input" id="total_seats" name="total_seats"
							min="1" value="<%=bus.getTotalSeats()%>" required />
					</div>

					<div class="input-box">
						<label for="departure_time" class="details">Departure Time
							(HH:MM):</label> <input type="time" class="input" id="departure_time"
							name="departure_time" value="<%=bus.getDepartureTime()%>"
							required />
					</div>

					<div class="input-box">
						<label for="arrival_time" class="details">Arrival Time
							(HH:MM):</label> <input type="time" class="input" id="arrival_time"
							name="arrival_time" value="<%=bus.getArrivalTime()%>" required />
					</div>

					<div class="input-box">
						<label for="seats_available" class="details">Seats
							Available:</label> <input type="number" class="input"
							id="seats_available" name="seats_available" min="0"
							value="<%=bus.getSeatsAvailable()%>" required />
					</div>

					<div class="input-box">
						<label for="is_active" class="details">Currently Active:</label> <select
							class="input" id="is_active" name="is_active" required>
							<option value="true" <%=bus.isActive() ? "selected" : ""%>>Active</option>
							<option value="false" <%=!bus.isActive() ? "selected" : ""%>>Inactive</option>
						</select>
					</div>

				</div>

				<div class="button-container">
					<div class="button1">
						<input type="submit" value="Update Bus" />
					</div>
					<div class="button2">
						<input type="reset" value="Reset" />
					</div>

					<div class="button3">
						<a href="ListBusServlet"
							class="back-button-link"> ← Back to Bus Details List </a>
					</div>
				</div>
			</form>

			<%
			}
			%>
		</div>
	</div>

	<c:if test="${not empty updateSuccess}">
		<div id="toast" class="toast">Bus successfully updated!</div>
		<script>
        const toast = document.getElementById("toast");
        toast.classList.add("show");

        setTimeout(() => {
            toast.classList.remove("show");
            window.location.href = "BopDashboard.jsp";
        }, 1500);
    </script>
	</c:if>

	<script src="${pageContext.request.contextPath}/js/bus/GetBus.js"></script>

</body>
</html>