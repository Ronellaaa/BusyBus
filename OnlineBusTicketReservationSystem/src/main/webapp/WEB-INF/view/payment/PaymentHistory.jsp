<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.payment.Payment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="service.payment.PaymentServiceImpl"%>
<%@page import="service.payment.IPaymentService"%>  
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="styles/payment/PaymentHistory.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Management</title>
</head>
<body>
<%	
	//Fetch payments of the concerned client
	ArrayList <Payment> paymentList  = (ArrayList)request.getAttribute("payList");

	//Avoid NullPointerException
	if (paymentList == null) {
		paymentList = new ArrayList<Payment>(); // Empty list if null
	}
%>

<div class="table-container">
    <table>
        <thead>
            <tr>
            	<th>Customer ID</th>
            	<th>Customer Name</th>
                <th>Payment ID</th>
                <th>Booking ID</th>
                <th>Card Holder</th>
                <th>Card Number</th>
                <th>Amount</th>
                <th>Payment Date & Time</th>
                <th>Status</th>
               
            </tr>
        </thead>
        <tbody>
        
            <%
                //Retrieve a payment
                for(Payment payment : paymentList){
            %>
            <tr>
            	<td><%=payment.getcusId()%></td>
                <td><%=payment.getcname()%></td>
                <td><%=payment.getPaymentId()%></td>
                <td><%=payment.getBookingId()%></td>
                <td><%=payment.getCardHolderName()%></td>
                <td><%=payment.getCardNumber()%></td>
                <td><%=String.format("%.2f", payment.getAmount())%></td>
                <td><%=payment.getFormattedPaymentDateTime()%></td>
                <td><%=payment.getPaymentStatus()%></td>
              </tr>
            <%
            	}
            %>
        </tbody>
    </table>
</div>

</body>
</html>