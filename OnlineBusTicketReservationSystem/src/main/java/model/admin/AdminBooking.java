package model.admin;
import java.time.LocalDate;

public class AdminBooking {
	
	private int bookingId;
	private LocalDate journeyDate;
	private String seats;
	private String seatType;
	private String bookingStatus;
	private double totalPrice;
	private int noSeats;
	private AdminCus cust;
	private AdminBus bus;

	
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	
	public LocalDate getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getNoSeats() {
		return noSeats;
	}
	public void setNoSeats(int noSeats) {
		this.noSeats = noSeats;
	}
	public AdminCus getCust() {
		return cust;
	}
	public void setCust(AdminCus cust) {
		this.cust = cust;
	}
	public AdminBus getBus() {
		return bus;
	}
	public void setBus(AdminBus bus) {
		this.bus = bus;
	}

	
	
	
	
	
	
	

}
