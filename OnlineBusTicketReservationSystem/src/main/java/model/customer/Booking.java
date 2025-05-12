package model.customer;

public class Booking {
 
	private int bookingId;
	private int customerId;
	private int busId;
	private String seats;
	private double totalPrice;
	private String travelDate;
	private String bookingStatus;
    private String paymentStatus;
    private String startLocation;
    private String endLocation;

	
	
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
    public String getStartLocation() {
        return startLocation;
    }
    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }
    public String getEndLocation() {
        return endLocation;
    }
    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
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

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
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

	

}
