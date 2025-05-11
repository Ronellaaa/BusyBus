<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Bus Operator Dashboard</title>

<!-- Link to custom dashboard CSS -->
<link rel="stylesheet" href="styles/bus/BopDashboard.css" />
</head>

<body>
	<c:if test="${param.message == 'addSuccess'}">
		<div class="popup" id="">Bus was successfully updated.</div>
	</c:if>
	
	<!-- ===== Header Bar ===== -->
	<div class="header-bar">
		<div class="brand-container">
			<div class="brand-name">BusyBus</div>
		</div>

		<div class="header-actions">

			<div class="user-profile">
				<div class="user-avatar">BoP1</div>
			</div>
		</div>
	</div>

	<!-- ===== Sidebar Navigation ===== -->
	<div class="sidebar">
		<a class="nav-item active" href="#dashboard">
			<div class="nav-icon">📊</div>
			<div class="nav-label">Dashboard</div>
		</a> <a class="nav-item" href="#bus-details">
			<div class="nav-icon">🚎</div>
			<div class="nav-label">Bus Details</div>
		</a> <a class="nav-item" href="ListBusServlet">
			<div class="nav-icon">📝</div>
			<div class="nav-label">Edit/Update</div>
		</a> <a class="nav-item" href="BusCardsServlet">
			<div class="nav-icon">👥</div>
			<div class="nav-label">Customer View</div>
		</a>
	</div>

	<!-- ===== Main Content Section ===== -->
	<div class="main-content">

		<!-- === Dashboard Header === -->
		<div class="dashboard-header" id="dashboard">
			<div>
				<h1 class="dashboard-title">Bus Operator Analytics</h1>
				<p class="dashboard-subtitle">Bus Operator Dashboard - Overview
					of your Bus Operations</p>
			</div>
			<div style="display: flex">
				<div class="date-filter">
					<span id="current-date"></span>
				</div>
				<div class="filter-button">
					<a href="BusForm.jsp" class="button-link no-underline">Add a
						new bus</a>
				</div>
			</div>
		</div>

		<!-- === Quick Stats Grid (can be dynamic in future) === -->
		<div class="dashboard-grid">
			<div class="quick-stats">
				<!-- Active No. of Buses -->
				<div class="stat-card">
					<div class="stat-icon blue-gradient">🚌</div>
					<div class="stat-title">Active No. of Buses</div>
					<div class="stat-value">${activeBuses}</div>
					<div class="stat-badge badge-blue">View Bus Details List
						Below!</div>
				</div>

				<!-- Total Buses -->
				<div class="stat-card">
					<div class="stat-icon purple-gradient">🚍</div>
					<div class="stat-title">Total Buses</div>
					<div class="stat-value">${totalBuses}</div>
					<div class="stat-badge badge-purple">Your Total Bus Count!</div>
				</div>

				<!-- Upcoming Departures Today -->
				<div class="stat-card">
					<div class="stat-icon green-gradient">⏰</div>
					<div class="stat-title">Upcoming Departures</div>
					<div class="stat-value">${upcomingDeparturesToday}</div>
					<div class="stat-badge badge-green">Your Upcoming Departures Today!</div>
				</div>

				<!-- Bus Types in Fleet -->
				<div class="stat-card">
					<div class="stat-icon orange-gradient">🧾</div>
					<div class="stat-title">Available Bus Types</div>
					<div class="stat-value">5 Types</div>
					<div class="stat-badge badge-orange">Luxury, Semi-Luxury, AC,
						Non-AC, Normal</div>
				</div>
			</div>
		</div>

		<!-- === Bus Details Section === -->
		<div class="Bus-header" id="bus-details">
			<div>
				<h1 class="b-title">Bus Details List</h1>
				<p class="b-subtitle">Bus List - Overview of your Bus List</p>
			</div>
		</div>
		<!-- Include the reusable table partial for buses -->
		<c:set var="showActions" value="false" />
		<c:import url="/WEB-INF/view/partials/BusTable.jsp" />

		<!-- Custom JavaScript for dashboard functionality -->
	<script src="${pageContext.request.contextPath}/js/bus/BopDashboard.js"></script>
 
 
 </body>
</html>