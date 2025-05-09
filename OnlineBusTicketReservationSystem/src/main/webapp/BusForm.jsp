<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Bus Details</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="styles/bus/BusForm.css" />
</head>
<body>
	<div class="busForm">
		<div class="container">

			<%
			String errorMessage = (String) request.getAttribute("errorMessage");
			if (errorMessage != null) {
			%>
			<div style="color: red; font-weight: bold; margin-bottom: 10px;">
				<%=errorMessage%>
			</div>
			<%
			}
			%>

			<!-- Decide form action dynamically -->
			<form method="POST"
				action="${empty bus ? 'AddBusServlet' : 'UpdateBusServlet'}">
				<div class="title">Bus Details</div>
				<br />

				<div class="busDetails">

					<!-- Hidden field for update -->
					<c:if test="${not empty bus}">
						<input type="hidden" name="bus_id" value="${bus.busId}" />
					</c:if>

					<div class="input-box">
						<label for="bus_name" class="details">Bus Name:</label> <input
							type="text" class="input" id="bus_name" name="bus_name"
							placeholder="Ex: Super Express" value="${bus.busName}" required />
					</div>

					<div class="input-box">
						<label for="bus_type" class="details">Bus Type:</label> <select
							class="input" id="bus_type" name="bus_type" required>
							<option value="">-- Select Bus Type --</option>
							<option value="Luxury"
								${bus.busType == 'Luxury' ? 'selected' : ''}>Luxury</option>
							<option value="Semi-Luxury"
								${bus.busType == 'Semi-Luxury' ? 'selected' : ''}>Semi-Luxury</option>
							<option value="AC" ${bus.busType == 'AC' ? 'selected' : ''}>AC</option>
							<option value="Non-AC"
								${bus.busType == 'Non-AC' ? 'selected' : ''}>Non-AC</option>
							<option value="Normal"
								${bus.busType == 'Normal' ? 'selected' : ''}>Normal</option>
						</select>
					</div>

					<div class="input-box">
						<label for="price_per_seat" class="details">Price per Seat
							(LKR):</label> <input type="number" class="input" id="price_per_seat"
							name="price_per_seat" step="0.01" min="0"
							value="${bus.pricePerSeat}" required />
					</div>

					<div class="input-box">
						<label for="total_seats" class="details">Total Seats:</label> <input
							type="number" class="input" id="total_seats" name="total_seats"
							min="1" value="${bus.totalSeats}" required />
					</div>

					<div class="input-box">
						<label for="departure_time" class="details">Departure Time
							(24H):</label> <input type="time" class="input" id="departure_time"
							name="departure_time" value="${bus.departureTime}" required />
					</div>

					<div class="input-box">
						<label for="arrival_time" class="details">Arrival Time
							(24H):</label> <input type="time" class="input" id="arrival_time"
							name="arrival_time" value="${bus.arrivalTime}" required />
					</div>

					<div class="input-box">
						<label for="seats_available" class="details">Seats
							Available:</label> <input type="number" class="input"
							id="seats_available" name="seats_available" min="0"
							value="${bus.seatsAvailable}" required />
					</div>

					<div class="input-box">
						<label class="details" for="is_active">Currently Active:</label> <select
							id="is_active" name="is_active" class="input" required>
							<option value="true" ${bus.active ? 'selected' : ''}>Active</option>
							<option value="false" ${!bus.active ? 'selected' : ''}>Inactive</option>
						</select>
					</div>


				</div>

				<div class="button-container">
					<div class="button1">
						<input type="submit" value="Submit" />
					</div>
					<div class="button2">
						<input type="reset" value="Cancel" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<script src="js/bus/BusForm.js"></script>
</body>
</html>
