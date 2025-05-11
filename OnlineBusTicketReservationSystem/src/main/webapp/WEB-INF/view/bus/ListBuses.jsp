<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.bus.Bus"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="service.bus.BusServiceImpl"%>
<%@ page import="service.bus.IBusService"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Bus List</title>
<link rel="stylesheet" href="styles/bus/ListBuses.css" />

<!-- Add this inline style to correctly load background image -->


</head>
<body>

	<!--  Delete success popup (auto-fades) -->
	<c:if test="${param.message == 'deleteSuccess'}">
		<div class="popup">Bus was successfully deleted.</div>
	</c:if>

	<!-- === Bus Details Section === -->
	<div class="table-header" id="list-details">

		<h1 class="l-title">Bus Details List</h1>
		<div class="left-buttons">
			<a href="BusForm.jsp" class="dashboard-btn">Add a New Bus</a> <a
				href="BopDashboardServlet" class="dashboard-btn">Go to Dashboard</a>
		</div>
	</div>
	
	<!-- === Bus Search Filter Form === -->
	<form action="SearchBusServlet" method="get" class="filter-form">
		<input type="text" name="busName" placeholder="Search by name..." class="filter-input"/>

		<select name="busType" class="filter-select">
			<option value="">All Types</option>
			<option value="AC">AC</option>
			<option value="Non-AC">Non-AC</option>
			<option value="Luxury">Luxury</option>
			<option value="Semi-Luxury">Semi-Luxury</option>
			<option value="Normal">Normal</option>
		</select>

		<select name="isActive" class="filter-select">
			<option value="">All Status</option>
			<option value="true">Active</option>
			<option value="false">Inactive</option>
		</select>

		<button type="submit" class="dashboard-btn">Search</button>
	</form>
	
	<!--  Enable action buttons (Edit/Delete) -->
	<%
	request.setAttribute("showActions", true);
	%>

	<jsp:include page="/WEB-INF/view/partials/BusTable.jsp" />

	<!-- Delete Confirmation Modal -->
	<div id="deleteModal" class="modal">
		<div class="modal-content">
			<p>Are you sure you want to delete this bus?</p>
			<form id="deleteForm" action="DeleteBusServlet" method="post">
				<input type="hidden" name="bus_id" id="bus_id">
				<button type="button" class="action-button" onclick="closeModal()">Cancel</button>
				<button type="submit" class="action-button delete-btn">Yes,
					Delete</button>
			</form>
		</div>
	</div>

<script src="${pageContext.request.contextPath}/js/bus/ListBuses.js"></script>


</body>
</html>
