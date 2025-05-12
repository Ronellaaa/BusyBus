<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="CSS/font.css" />
<%-- Makes the website match your the device's screen width and shows the website at normal size--%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Payment Form</title>
<link rel="stylesheet" href="styles/payment/Submit.css">
</head>

<body>
<form method="POST" action="AddPaymentServlet">
	<div class="payment-container">
	        <h2>Complete Your Booking</h2>
	        
	        <input type="hidden" name="bookingId" value="3">
	        
	        <div id="card-details-section">
	            
	            <div class="form-group card">
	                <label for="card-number">Card Number</label>
	                <i class='bx bxs-credit-card'></i>
	                <input type="text" id="card-number" name="cardNumber" placeholder="1234 5678 9012 3456" pattern="[\d ]{13,23}" required>
	         
	            </div>
	          
	            <div class="form-group">
	                <label for="card-name">Name on Card</label>
	                <input type="text" id="card-name" name="cardHolderName" placeholder="John Doe" required>
	               
	            </div>
	            <div class="row">
	            
	                <div class="form-group half-width">
	                    <label for="expiry">Expiry Date</label>
	                    <input type="date" id="expiry" name="expiryDate"required>
	                </div>
	                
	                <div class="form-group half-width">
	                    <label for="cvv">CVV</label>
	                    <input type="password" id="cvv" name="cvv" placeholder="123" required>
	                </div>
	                
	            </div>
	            
	        </div>
	        
	        <input type="hidden" name="amount" value="100">
	        
	        <!-- Terms and Submit Button -->
	       <div class="terms">
	           <label>
	               <input type="checkbox" id="terms-checkbox" required> I agree to the terms and conditions
	           </label>
	       </div>
	       
	       <input id="pay-button"  class="pay-button" type="submit" value="Proceed to Pay $60.00" disabled>
      </div>
  </form>
  
 <script src="js/payment/Submit.js"></script>
 
</body>
</html>

