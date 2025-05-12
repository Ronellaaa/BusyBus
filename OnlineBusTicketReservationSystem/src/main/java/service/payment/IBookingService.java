package service.payment;
import model.payment.Booking;

import java.util.ArrayList;
import java.util.logging.Logger;  // Java's built-in logger



public interface IBookingService {
	//Create a logger object to log the errors
	public static final Logger log = Logger.getLogger(IPaymentService.class.getName());
	
	public ArrayList<Booking> getBookingsById(int id);
	public void updateBooking(int BookingID,String status);

}
