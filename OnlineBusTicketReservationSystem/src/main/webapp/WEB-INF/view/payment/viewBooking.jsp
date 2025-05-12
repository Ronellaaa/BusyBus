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
<link rel="stylesheet" type="text/css" href="styles/payment/bookings.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
//Cache control headers to prevent browser caching
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", 0); // Proxies

	//Get the coresponding booking list from the session
    ArrayList<Booking> bookList = (ArrayList<Booking>) session.getAttribute("bookingListForPayment");
   
	//Initialize booking
    Booking booking = null;
  
	//Avoid NullPointerException
    if(!bookList.isEmpty()){
   		booking = bookList.get(0); 
    }
%>
  
    <div class="table-container"">
   	<table>
        <thead>
            <tr style="height: 100px">
                <th style="width: 100px">Booking ID</th>
                <th style="width: 200px">Booking status</th>
                <th style="width: 50%">Action</th>
            </tr>
            </thead>
            
            <tbody>
            <tr style="height: 200px">
            	<td><%=booking.getBookingID()%></td>
            	<td>
            		<span class="status-badge status-<%=booking.getBookingStatus().toLowerCase()%>"><%=booking.getBookingStatus()%></span>
            	</td> 
                    
                 <td>
                 <div class="action-buttons">
	               <div id="form-<%=booking.getBookingID()%>" class="edit-form">
	               
		               <form method="POST" action="UpdateBookingServlet">
		                   <input type="hidden" name="bookingID" value="<%=booking.getBookingID()%>"/>
		                   
		                   <div class="form-group">
		                       <select name="status" class="dropdown">
	                           <option value="Pending">Pending</option>
	                           <option value="Confirmed">Confirmed</option>
	                           <option value="Cancelled">Cancelled</option>     
                                </select>
                                 
                                <button type="submit" class="btn btn-sm btn-primary">Save</button>
                               
                            </div>
                        </form>
                    </div>
                  </div>
                  </td>
              </tr>
              </tbody>          
    </table>           
  </div>          

<script src="js/payment/actionButtonsV.js"></script>

</body>
</html>