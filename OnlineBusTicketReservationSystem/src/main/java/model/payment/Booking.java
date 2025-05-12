package model.payment;

public class Booking {
	private int bookingID;
	private String bookingStatus = "Pending";
	
	// Constructors
	 public Booking() {
	     // Default constructor
	 }
	 
	 public void setBookingID(int bookingID) {
			this.bookingID = bookingID;
		}
	 
	 public int getBookingID() {
			return bookingID;
		}
	 
	 public String getBookingStatus() {
			return bookingStatus;
		}

		public void setBookingStatus(String bookingStatus) {
			this.bookingStatus = bookingStatus;
		}

}	

