package model.payment;
import java.time.*;
import java.time.format.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Payment {
 private int paymentId;
 private int cusId;
 private String cname;
 private int bookingId;
 private String cardNumber;
 private String cardHolderName;
 private String expiryDate;
 private String cvv;
 private double amount;
 private LocalDateTime paymentDateTime = LocalDateTime.now();
 private String paymentStatus = "Pending";
 private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");

 // Constructors
 public Payment() {
     // Default constructor
 }

 public Payment(int bookingId, String cardNumber, String cardHolderName, String expiryDate, String cvv, double amount) {
     this.bookingId = bookingId;
     this.cardNumber = cardNumber;
     this.cardHolderName = cardHolderName;
     this.expiryDate = expiryDate;
     this.cvv = cvv;
     this.amount = amount;
     this.paymentDateTime = LocalDateTime.now();
     this.paymentStatus = "Pending";
 }
 
//Formatted getter for display (Utility function)
 public String getFormattedPaymentDateTime() {
     if(paymentDateTime != null ){
    	  return paymentDateTime.format(FORMATTER);
     }
     else {
    	 return "Not specified";
     }
           
         
 }
 
 public void setcusId(int cid) {
		this.cusId = cid;
	}
 
 public int getcusId() {
		return cusId;
	}
 
 public void setcname(String cname) {
		this.cname = cname;
	}
 
 public String getcname() {
		return cname;
	}

public int getPaymentId() {
	return paymentId;
}

public void setPaymentId(int paymentId) {
	this.paymentId = paymentId;
}

public int getBookingId() {
	return bookingId;
}

public void setBookingId(int bookingId) {
	this.bookingId = bookingId;
}

public String getCardNumber() {
	return cardNumber;
}

public void setCardNumber(String cardNumber) {
	this.cardNumber = cardNumber;
}

public String getCardHolderName() {
	return cardHolderName;
}

public void setCardHolderName(String cardHolderName) {
	this.cardHolderName = cardHolderName;
}

public String getExpiryDate() {
	return expiryDate;
}

public void setExpiryDate(String expiryDate) {
	this.expiryDate = expiryDate;
}

public String getCvv() {
	return cvv;
}

public void setCvv(String cvv) {
	this.cvv = cvv;
}

public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}

public LocalDateTime getPaymentDateTime() {
	return paymentDateTime;
}

public void setPaymentDateTime(LocalDateTime paymentDateTime) {
	this.paymentDateTime = paymentDateTime;
}

public String getPaymentStatus() {
	return paymentStatus;
}

public void setPaymentStatus(String paymentStatus) {
	this.paymentStatus = paymentStatus;
}

}