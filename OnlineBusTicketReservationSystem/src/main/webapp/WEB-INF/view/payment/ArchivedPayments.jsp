<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.payment.Payment"%>
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

<link rel="stylesheet" type="text/css" href="styles/payment/tables.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Management</title>
</head>

<body>
<%
	//Fetch the list of archived payments
	ArrayList <Payment> archivedList  = (ArrayList)request.getAttribute("archivedPayments");

	//Avoid NullPointerException
	if (archivedList == null) {
    	archivedList = new ArrayList<Payment>(); // Empty list if null
	}
%>

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

<br>
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
                <th>Action</th>               
            </tr>
        </thead>
        
        <tbody>
            <%
                //Retrive an archived payment
                for(Payment payment : archivedList){
            %>
            <tr>
                <td><%=payment.getPaymentId()%></td>
                <td><%=payment.getBookingId()%></td>
                <td><%=payment.getCardHolderName()%></td>
                <td><%=payment.getCardNumber()%></td>
                <td><%=String.format("%.2f", payment.getAmount())%></td>
                <td><%=payment.getFormattedPaymentDateTime()%></td>
                <td><%=payment.getPaymentStatus()%></td>
                <td> 
	                <div class="action-buttons">
	                
	                <form method="POST" action="archivesDeleteServlet" style="display: inline;">
	                     <input type="hidden" name="paymentID" value="<%=payment.getPaymentId()%>"/>
	                     <button type="submit" class="btn btn-sm btn-danger" id="transbtn"><i class='bx bxs-trash'></i></button>
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

</body>
</html>