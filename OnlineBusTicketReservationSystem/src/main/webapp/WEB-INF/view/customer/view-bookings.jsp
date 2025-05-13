<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="java.util.*, model.customer.Booking" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Bookings</title>
    <style>
        table {
            border-collapse: collapse;
            width: 90%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        h2 {
            text-align: center;
            color: #333;
        }
    </style>
</head>
<body>

<h2>All Bookings</h2>

<%
    List<Booking> bookingList = (List<Booking>) request.getAttribute("bookingList");

    if (bookingList == null || bookingList.isEmpty()) {
%>
        <p style="text-align:center;">No bookings found.</p>
<%
    } else {
%>
<table>
    <tr>
        <th>Booking ID</th>
        <th>Customer ID</th>
        <th>Bus ID</th>
        <th>Seats</th>
        <th>Total Price</th>
        <th>Travel Date</th>
        <th>Booking Status</th>
        <th>Payment Status</th>
    </tr>
<%
        for (Booking b : bookingList) {
%>
    <tr>
        <td><%= b.getBookingId() %></td>
        <td><%= b.getCustomerId() %></td>
        <td><%= b.getBusId() %></td>
        <td><%= b.getSeats() %></td>
        <td><%= b.getTotalPrice() %></td>
       <%--  <td><%= b.getJourneyDate() %></td> --%>
        <td><%= b.getBookingStatus() %></td>
        <td><%= b.getPaymentStatus() %></td>
    </tr>
<%
        }
    }
%>
</table>

</body>
</html>

 
 
