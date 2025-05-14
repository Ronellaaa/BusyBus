package service.payment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
// For database connections
// For getting connections (if not using a utility class)
// For simple SQL queries
// For parameterized queries (safe from SQL injection)
// For handling SELECT query results
// For catching database errors
import java.sql.*;

import model.payment.Booking;
import model.payment.Payment;
//import com.oop.util.DBConnectionUtil;
//import com.oop.util.*;
import util.DBConnection;

public class BookingServiceImpl implements IBookingService  {
	 private Connection connection;
	 private PreparedStatement preparedStatement;
	 int generatedId;
	
	 //Retrieve bookings by the booking id of the corresponding payment
	public ArrayList<Booking> getBookingsById(int id){
		    ArrayList<Booking> BookingList = new ArrayList<>();
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			 try {
				 	//Assign connection the Connection object retrieved from the Singleton pattern
			        connection = DBConnection.getInstance().getConnection();
			        
			        //Set the preparedStatement based on the query to retrieve bookings by the booking id
			        preparedStatement = connection.prepareStatement("SELECT booking_id,bookingStatus FROM bookings WHERE booking_id = ?");
			        preparedStatement.setInt(1, id);
			        
			        //Set the resultSet to the retrieved rows from the query
		            resultSet = preparedStatement.executeQuery();
		            
		            //Retrieve the result one by one
			    	while (resultSet.next()) {
			    		//Create a booking object store the details from the result
			    		Booking booking =new Booking();
			    		
			    		//Set the booking details
			    		booking.setBookingID(resultSet.getInt("booking_id"));
			    		booking.setBookingStatus(resultSet.getString("bookingStatus"));
			    		
			    		//Add the Booking object to the list of Booking objects
			    		BookingList.add(booking);
			    	}
	    	  
			    //Catch the errors related to execution of queries.	
			 } catch (SQLException  e) {
			        log.log(Level.SEVERE, "Error retrieving booking status: " + e.getMessage());
			        
			     //Close the resources in the end   
			    } finally {
			    	
			        // Close resources which are already opened in reverse order of creation
			        try {
			            if (resultSet != null) resultSet.close();
			            
			            if (preparedStatement != null) preparedStatement.close();
			            
			            if (connection != null) connection.close();
			            
			          //Catch the errors encountered when closing the resources. 
			        } catch (SQLException e) {
			            log.log(Level.SEVERE, "Error closing resources: " + e.getMessage());
			        }
			    }
			 
			 	//Return the list of Booking objects
			    return BookingList;
	}
	
	//Update the booking status based on the inputs
	public void updateBooking(int BookingID,String status) {
		try {
			//Assign connection the Connection object retrieved from the Singleton pattern
	        connection = DBConnection.getInstance().getConnection();
	        
	      //Set the preparedStatement based on the query to update the booking status
	        preparedStatement = connection.prepareStatement( "UPDATE bookings SET bookingStatus = ? WHERE booking_id = ?" );
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, BookingID);
            
            preparedStatement.executeUpdate();
            //connection.commit(); // Commit transaction
            
	 }
	   //Catch the errors related to execution of queries.
	   catch (SQLException  e) {
	        log.log(Level.SEVERE, "Error updating  booking status: " + e.getMessage());
	    } 
		//Close the resources in the end   
		finally {
			
			// Close resources which are already opened in reverse order of creation
	        try {
	            if (preparedStatement != null) preparedStatement.close();
	            
	            if (connection != null) connection.close();
	            
	            //Catch the errors encountered when closing the resources.   
	        } catch (SQLException e) {
	            log.log(Level.SEVERE, "Error closing resources: " + e.getMessage());
	        }
	    }
	}
}
