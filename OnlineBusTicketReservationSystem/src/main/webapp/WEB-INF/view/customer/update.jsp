<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String name = (String) session.getAttribute("name");
    String email = (String) session.getAttribute("email");
    String phone = (String) session.getAttribute("phone");
    
    String[] nameParts = name != null ? name.split(" ") : new String[0];
    String firstName = nameParts.length > 0 ? nameParts[0] : "";
    String lastName = nameParts.length > 1 ? nameParts[1] : "";

%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Update Profile</title>
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link rel="stylesheet" href="styles/customer/update.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap"
      rel="stylesheet"
    />
   
  </head>
  <body>
  <jsp:include page="/Navbar.jsp" />
    <div class="background">
      <div class="shape"></div>
      <div class="shape"></div>
    </div>
<div class="update-container">
   <form action="UpdateCustomer" method="post" >
      <input type="hidden" name="action" value="update" />

      <h2>💾 Update Profile</h2>

      <div class="form-group">
        <input
          type="text"
          id="firstName"
          name="firstName"
          required
          placeholder=" "
          value="<%= firstName %>"
        />
        <label for="firstName">First Name</label>
      </div>

      <div class="form-group">
        <input
          type="text"
          id="lastName"
          name="lastName"
          required
          placeholder=" "
          value="<%= lastName %>"
        />
        <label for="lastName">Last Name</label>
      </div>

      <div class="form-group">
        <input
          type="tel"
          id="phone"
          name="phone"
          required
          placeholder=" "
          value="<%= phone != null ? phone : "" %>"
        />
        <label for="phone">Phone Number</label>
      </div>

      <div class="form-group">
        <input
          type="email"
          id="email"
          name="email"
        
          placeholder=" "
          value="<%= email != null ? email : "" %>"
        />
        <label for="email">Email</label>
      </div>

      <div class="form-group">
        <input type="password" id="password" name="password" placeholder=" " />
        <label for="password">New Password</label>
      </div>

      <button type="submit" class="btn-update">✨ Save Changes</button>
    </form>
    </div>
   <%--  <jsp:include page="/footer.jsp" /> --%>
  </body>
</html>
