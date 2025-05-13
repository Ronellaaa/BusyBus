<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bus Cards View</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/bus/BusCards.css" />
</head>
<body>
<jsp:include page="/Navbar.jsp"/>


<div style="margin: 20px;">
<!-- <a href="BopDashboardServlet"> -->
    <button onclick="history.back()" class="back-button">← Back</button>
   <!--  </a> -->
</div>


	<div class="bus-card-page">

		<!-- Error message (if any) -->
		<c:if test="${not empty errorMessage}">
			<div class="error-message">${errorMessage}</div>
		</c:if>

		<!-- Bus Cards Container -->
		<div class="bus-card-container">

			<!-- Loop through all buses in busList -->
			<c:forEach var="bus" items="${busList}">
				<div class="bus-card">

					<!-- Header: Bus Name, Active Status, Type and Seats Info -->
					<div class="bus-card-header">
						<div class="bus-header-left">
							<h2>
								${bus.busName}
								<span class="status-indicator ${bus.active ? 'active' : 'inactive'}"
									  title="${bus.active ? 'Active' : 'Inactive'}"></span>
							</h2>
							<p class="bus-subinfo">
								${bus.busType} | ${bus.seatsAvailable} seats available
							</p>

							<!-- NEW: Display Route Name if available -->
							<c:if test="${not empty bus.routeName}">
								<p class="bus-route-name">🛣️ Route: ${bus.routeName}</p>
							</c:if>
						</div>

						<!-- Price per seat -->
						<div class="bus-price">LKR. ${bus.pricePerSeat}</div>
					</div>

					<!-- Times Section -->
					<div class="bus-times">
						<span><strong>Departure:</strong> ${bus.departureTime}</span>
						<span class="arrow">→</span>
						<span><strong>Arrival:</strong> ${bus.arrivalTime}</span>
					</div>

					<!-- Book Now Button (only if active and has seats) -->
					<div class="bus-card-actions">
						<c:choose>
							<c:when test="${bus.active and bus.seatsAvailable > 0}">
								<!-- <form action="book-seat" method="post" class="inline-form"> -->
								
									<input type="hidden" name="bus_id" value="1" />
								<!-- 	<button type="submit" class="action-button book-btn">Book Now</button> -->
									 <a href="book-seat?bus_id=${bus.busId}">
                                  <button class="action-button book-btn">Book Now</button>
                                    </a>
								
							<!-- 	</form> -->
							</c:when>
							<c:otherwise>
								<button class="action-button book-btn disabled" disabled>Currently Unavailable</button>
							</c:otherwise>
						</c:choose>
					</div>

				</div>
			</c:forEach>

		</div>
	</div>

</body>
</html>