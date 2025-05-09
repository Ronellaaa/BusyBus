
<html>
<head>
 <title>Bus Seat Booking</title>
    <link rel="stylesheet" href="styles/customer/booking.css">
  </head>
<body>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    List<String> bookedSeats = (List<String>) request.getAttribute("bookedSeats");
    if (bookedSeats == null) {
        bookedSeats = new ArrayList<>();
    }
    String name = (String) session.getAttribute("name");
    String email = (String) session.getAttribute("email");
    String phone = (String) session.getAttribute("phone");
%>
<div class="container">
    <div class="bus-layout">
        <h2>Seat Layout</h2>
        <div id="seatLayout"></div>
        <input type="hidden" id="bookedSeatsData" value="<%
            for (int i = 0; i < bookedSeats.size(); i++) {
                String seat = bookedSeats.get(i).trim();
                String numberOnly = seat.contains("-") ? seat.split("-")[0] : seat;
                out.print(numberOnly);
                if (i < bookedSeats.size() - 1) out.print(",");
            }
        %>" />
    </div>

    <div class="seat-details">
        <h2>Booking Details</h2>
        <p>Selected Seats: <span id="selectedSeatsText">None</span></p>
        <p>Total Price: <span id="totalPrice">0 LKR</span></p>
        <form action="book-seat" method="post">
            <input type="hidden" name="busId" value="<%= request.getAttribute("busId") %>"/>
            <input type="hidden" name="routeId" value="<%= request.getAttribute("routeId") %>"/>
            <input type="hidden" name="travelDate" value="<%= request.getAttribute("travelDate") %>"/>

            <label>Name:</label>
            <input type="text" name="name" value="<%= name != null ? name : "" %>" required><br>

            <label>Email:</label>
            <input type="email" name="email" value="<%= email != null ? email : "" %>" required><br>

            <label>Phone:</label>
            <input type="text" name="phone" value="<%= phone != null ? phone : "" %>" required><br>

            <input type="hidden" name="seats" id="seatInput" readonly required>
            <input type="hidden" name="totalPrice" id="priceInput" readonly required>

            <label>Booking Status:</label>
            <input type="text" name="bookingStatus" value="Pending" readonly><br>

            <label>Payment Status:</label>
            <input type="text" name="paymentStatus" value="Pending" readonly><br>

            <button type="submit">Confirm Booking</button>
        </form>
    </div>
</div>

<script>
  const bookedSeats = <% if (bookedSeats != null) { %> [
    <%
      for (int i = 0; i < bookedSeats.size(); i++) {
          out.print("\"" + bookedSeats.get(i) + "\"");
          if (i < bookedSeats.size() - 1) out.print(",");
      }
    %>
  ] <% } else { %> [] <% } %>;
  console.log("Booked Seats from DB:", bookedSeats);
</script>
<script src="js/customer/booking.js"></script>
</body>
</html>
  