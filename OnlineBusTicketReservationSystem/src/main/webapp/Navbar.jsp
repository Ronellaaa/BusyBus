<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    String firstName = (String) session.getAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="styles/customer/navbar.css" />
</head>
<body>
   <!-- ð¹ HEADER -->
      <div class="container2">
        <div class="logo">
          <img src="assets/ba.png" alt="logo image" />
        </div>
        <nav>
          <ul>
            <li>
              <a href="<%= request.getContextPath() %>/Homepage.jsp" class="link">Home</a>
            <a href="<%= request.getContextPath() %>/AboutUs.jsp" class="link">About Us</a>
              <a href="BusCardsServlet" class="link"> Journeys </a>
               <a href="<%= request.getContextPath() %>/contactUs.jsp" class="link">Contact Us</a>
            </li>
          </ul>
        </nav>
      <div class="login-button6">
    <% if (firstName != null) { %>
        <a href="profile">
            <button class="btn-login6">
                <i class="fa-solid fa-user"></i> Profile
            </button>
        </a>
        <a href="BopLogoutServlet">
            <button class="btn-login6">
                Logout
            </button>
        </a>
    <% } else { %>
        <a href="sign-up">
            <button class="btn-login6">
                <i class="fa-solid fa-user"></i> Sign Up
            </button>
        </a>
        <a href="login">
            <button class="btn-login6">
                <i class="fa-solid fa-user"></i> Login
            </button>
        </a>
    <% } %>
</div>
        </div>
    
</body>
</html>