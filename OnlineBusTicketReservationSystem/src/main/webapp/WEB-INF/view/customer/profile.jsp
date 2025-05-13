<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.customer.Booking" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Profile Page</title>
  <link rel="stylesheet" href="styles/customer/profile.css" />
    <script src="https://unpkg.com/@dotlottie/player-component@2.7.12/dist/dotlottie-player.mjs" type="module"></script>
     <script src="https://unpkg.com/@dotlottie/player-component@2.7.12/dist/dotlottie-player.mjs" type="module"></script>
</head>
<body>
<%
    String name = (String) session.getAttribute("name");
    String email = (String) session.getAttribute("email");
    String phone = (String) session.getAttribute("phone");
%>
<jsp:include page="/Navbar.jsp" />

<div style="margin: 20px;">
<!-- <a href="BopDashboardServlet"> -->
    <button onclick="history.back()" class="back-button">← Back</button>
   <!--  </a> -->
</div>
<div class="profile1">

<div class="profile-wrapper">

<div class="lottie-left">
 <img
      src="${pageContext.request.contextPath}/assets/il1.png"
      alt="User"
    />
  </div>


<div class="profile-card">
  <img
    src="https://raw.githubusercontent.com/hicodersofficial/glassmorphism-login-form/master/assets/illustration.png"
    alt="illustration"
    class="illustration"
  />
  <div class="avatar">
    <img
      src="https://cdn1.vectorstock.com/i/1000x1000/80/60/avatar-icon-on-black-round-flat-symbol-vector-21698060.jpg"
      alt="User"
    />
  </div>
  <div class="profile-name"><%= name != null ? name : "Guest User" %></div>
  <div class="profile-info">📧 <%= email != null ? email: "Not Available" %></div>
  <div class="profile-info">📱 <%=phone  != null ? phone: "Not Available" %></div>


  <a href="UpdateCustomer">
  <button class="btn-book">✏️ Update</button>
</a>
  <form action="deleteCustomer" method="post" onsubmit="return confirm('Are you sure you want to delete your profile?');">
  <button class="btn-book" style="background: #ff1f1f">🗑️ Delete</button>
  </form>
 
</div>
 <div class="lottie-right">
    
<dotlottie-player
  src="https://lottie.host/89c92821-0774-4459-85c8-6ac4ecbf2a42/Ej8OzABeZ2.lottie"
  background="transparent"
  speed="1"
  style="width: 300px; height: 300px"
  loop
  autoplay
></dotlottie-player>
  </div>

</div>

<h1 id="bookedTickets">Booked Tickets</h1>

<%
  List<Booking> bookingList = (List<Booking>) request.getAttribute("bookingList");

  if (bookingList == null || bookingList.isEmpty()) {
%>
  <p style="text-align:center;">No bookings found.</p>
<%
  } else {
%>
  <table class="bookings-table">
    <thead>
      <tr>
        <th>Booking ID</th>
        <th>Route</th>
         <th>Bus</th>
         <th>Bus Type</th>
        <th>Date</th>
        <th>Seat</th>
        <th>Status</th>
      </tr>
    </thead>
    <tbody>
      <%
        for (Booking b : bookingList) {
      %>
       <tr>
        <td><%= b.getBookingId() %></td>
        <td><%= b.getBusRouteName() %></td>
        <td><%= b.getBus().getBusName() %></td>
        <td><%= b.getBus().getBusType() %></td>
     
        <td><%= b.getJourneyDate() %></td>
        <td><%= b.getSeats() %></td>
        <td class="status-<%= b.getBookingStatus().toLowerCase() %>"><%= b.getBookingStatus() %></td>
      </tr>
      <%
        }
      %>
    
      
    </tbody>
  </table>

<%
  }
%>
  </div>
  
  <div class="cus">
  

<dotlottie-player src="https://lottie.host/bd77add0-7f61-4dbd-a511-fc326964046c/QS0anxn4vs.lottie" background="transparent" speed="1" style="width: 300px; height: 300px" loop autoplay></dotlottie-player>
  </div>
 <jsp:include page="/footer.jsp" /> 
</body>
</html>
