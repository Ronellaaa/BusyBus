package model.customer;

import model.bus.Bus;

public class Booking {
 
	private int bookingId;
	private int customerId;
	private int busId;
	private Bus bus ; 
	

	private String seats;
	private double totalPrice;
	private String journeyDate;
	private String bookingStatus;
    private String paymentStatus;
    private String busRouteName;
   
	private String seatType;
    private int noSeats;

	
	
//	public Booking(int bookingId, int customerId, int busId, String seats, double totalPrice, String travelDate, String bookingStatus, String paymentStatus) {
//		this.bookingId = bookingId;
//		this.customerId = customerId;
//		this.busId = busId;
//		this.seats = seats;
//		this.totalPrice = totalPrice;
//		this.travelDate = travelDate;
//		this.bookingStatus = bookingStatus;
//		this.paymentStatus = paymentStatus;
//	}
    
    
    public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public int getNoSeats() {
		return noSeats;
	}
	public void setNoSeats(int noSeats) {
		this.noSeats = noSeats;
	}

    
    
    
    public String getBusRouteName() {
        return busRouteName;
    }
    public void setBusRouteName(String busRouteName) {
        this.busRouteName = busRouteName;
    }
 

    public int getBookingId() {
		return bookingId;
	}



	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}


	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(String travelDate) {
		this.journeyDate = travelDate;
	}
	
	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Bus getBus() {
		return bus;
	}
	public void setBus(Bus bus) {
		this.bus = bus;
	}

}
