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
              <a href="<%=request.getContextPath()%>/Hompage.jsp" class="link"> Home </a>
              <a href="/about" class="link"> About Us </a>
              <a href="/menu" class="link"> Products </a>
              <a href="/contact" class="link"> Contact Us </a>
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
        <a href="LogoutServlet">
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