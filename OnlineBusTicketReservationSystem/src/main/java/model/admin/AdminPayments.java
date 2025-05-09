package model.admin;
import java.time.LocalDate;

public class AdminPayments {
	
	private int paymentId;
	private double amount;
	private LocalDate paymentDate;
	private String paymentStatus;
	private AdminCus cust;
	private AdminBooking booking;
	
	
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public AdminCus getCust() {
		return cust;
	}
	public void setCust(AdminCus cust) {
		this.cust = cust;
	}
	public AdminBooking getBooking() {
		return booking;
	}
	public void setBooking(AdminBooking booking) {
		this.booking = booking;
	}
	
	

	

}
