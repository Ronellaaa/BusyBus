<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="model.payment.Payment"%>
<%@page import="model.payment.Booking"%>
<%@page import="java.util.ArrayList"%>
<%@page import="service.payment.PaymentServiceImpl"%>
<%@page import="service.payment.IPaymentService"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="styles/payment/font.css" />
<link rel="stylesheet" type="text/css" href="styles/payment/tables.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Management</title>
</head>
<body>



<div class="notification-container" id="notificationContainer">
    <%
        // Get the notification from session
       Map <String, String> notification = (Map <String, String> ) session.getAttribute("notification");
    
    	//Create the notifications
        if (notification != null) {
          
    %>
    
    <div class="notification <%= notification.get("type") %>" data-id="<%= notification.get("id") %>">
        <span>
            <%= notification.get("message") %>
            <% if (notification.get("url") != null) { %>
       			 <a href="<%= notification.get("url") %>">Update now</a>
               
            <% 
            	}
            
            session.removeAttribute("notification");
            %>
        </span>
        
    </div>
    <%
            }

    %>
</div>

<div class="navbar">
<ul>
	<div class=" nav all"><li><a href="getPayServlet?type=all">All</a></li></div>
	<div class="nav pen"><li><a href="getPayServlet?type=pending">Pending</a></li></div>
	<div class="nav com"><li><a href="getPayServlet?type=completed">Completed</a></li></div>
	<div class="nav fai"><li><a href="getPayServlet?type=failed">Failed</a></li></div>
	<div class="nav ref"><li><a href="getPayServlet?type=refunded">Refunded</a></li></div>
	<div class="nav arc"><li><a href="getArchivedPaymentsServlet">Archives</a></li></div>
	<div class="nav log">
<li>

        <a href="BopLogoutServlet" class="logout-link">
            <button class="logout-btn">
                <i class="fas fa-sign-out-alt"></i> Logout
            </button>
        </a>

</li>
</div>
</ul>
</div>

<div class="table-container">
    <table>
        <thead>
            <tr>
                <th>Payment ID</th>
                <th>Booking ID</th>
                <th>Card Holder</th>
                <th>Card Number</th>
                <th>Amount</th>
                <th>Payment Date & Time</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                IPaymentService paymentService = new PaymentServiceImpl();
                ArrayList<Payment> paymentList = paymentService.getPayments();
                
                for(Payment payment : paymentList){
  			%>
            
            <tr>
                <td><%=payment.getPaymentId()%></td>
                <td><%=payment.getBookingId()%></td>
                <td><%=payment.getCardHolderName()%></td>
                <td><%=payment.getCardNumber()%></td>
                <td><%=String.format("%.2f", payment.getAmount())%></td>
                <td><%=payment.getFormattedPaymentDateTime()%></td>
                <td>
                    <span class="status-badge status-<%=payment.getPaymentStatus().toLowerCase()%>">
                        <%=payment.getPaymentStatus()%>
                    </span>
                </td>
                
                <td>
                    <div class="action-buttons">
                        <button class="btn btn-sm btn-outline" id="transbtn" onclick="showEditForm('<%=payment.getPaymentId()%>')">
                            <i class='bx bxs-edit-alt' ></i>
                        </button>
                        
                        <form method="POST" action="DeletePaymentServlet" style="display: inline;">
                            <input type="hidden" name="paymentID" value="<%=payment.getPaymentId()%>"/>
                            <button type="submit" class="btn btn-sm btn-danger" id="transbtn"><i class='bx bx-archive-in' ></i></button>
                        </form>
                        
                    </div>
                    
                    <div id="form-<%=payment.getPaymentId()%>" class="edit-form">
                    
                        <form method="POST" action="UpdatePaymentServlet">
                            <input type="hidden" name="paymentID" value="<%=payment.getPaymentId()%>"/>
                            <input type="hidden" name="bookingID" value="<%=payment.getBookingId()%>"/>
                            
                            <div class="form-group">
                                <select name="status">
                                    <option value="Pending">Pending</option>
                                    <option value="Completed">Completed</option>
                                    <option value="Failed">Failed</option>
                                    <option value="Refunded">Refunded</option>
                                </select>
                                
                                 <button type="button" class="btn btn-sm btn-outline" 
                                    onclick="cancelEdit('<%=payment.getPaymentId()%>')">Cancel</button>
                                <button type="submit" class="btn btn-sm btn-primary" onclick="showNotifications()">Save</button>
                               
                            </div>
                        </form>
                    </div>
                </td>
            </tr>
            <% 
            	} 
            %>
        </tbody>
    </table>
</div>

<script src="js/payment/actionButtonsV.js"></script>

</body>
</html>