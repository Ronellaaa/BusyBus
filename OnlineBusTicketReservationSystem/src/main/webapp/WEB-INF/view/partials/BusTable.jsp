
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Displaying any error messages -->
<c:if test="${not empty errorMessage}">
	<div style="color: red; font-weight: 600; margin-bottom: 15px;">${errorMessage}</div>
</c:if>

<!-- Styled Bus List Table -->
<div class="bus-table-container">
	<table class="bus-table">
		<thead>
			<tr>
				<th>Bus Name</th>
				<th>Type</th>
				<th>Price/Seat</th>
				<th>Total Seats</th>
				<th>Departure</th>
				<th>Arrival</th>
				<th>Available</th>
				<th>Status</th>
				<c:if test="${showActions}">
					<th>Actions</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bus" items="${busList}">
				<tr>
					<td>${bus.busName}</td>
					<td>${bus.busType}</td>
					<td>Rs.${bus.pricePerSeat}</td>
					<td>${bus.totalSeats}</td>
					<td>${bus.departureTime}</td>
					<td>${bus.arrivalTime}</td>
					<td>${bus.seatsAvailable}</td>
					<td><span
						class="${bus.active ? 'status-active' : 'status-inactive'}">
							${bus.active ? 'Active' : 'Inactive'} </span></td>
					<c:if test="${showActions}">
						<td class="action-cell">
							<form action="GetBusServlet" method="post" class="inline-form">
								<input type="hidden" name="bus_id" value="${bus.busId}" />
								<button type="submit" class="action-button edit-btn">Edit</button>
							</form>
							<button class="action-button delete-btn" type="button"
								onclick="confirmDelete('${bus.busId}')">Delete</button>
						</td>

					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>