package service.payment;
import java.io.IOException;
 
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;          // For database connections
import java.sql.DriverManager;       // For getting connections (if not using a utility class)
import java.sql.Statement;           // For simple SQL queries
import java.sql.PreparedStatement;   // For parameterized queries (safe from SQL injection)
import java.sql.ResultSet;           // For handling SELECT query results
import java.sql.SQLException;        // For catching database errors
import java.sql.*;
import model.payment.Payment;
//import com.oop.util.DBConnectionUtil;

import util.DBConnection;

public class PaymentServiceImpl implements IPaymentService{
	public static final Logger log = Logger.getLogger(PaymentServiceImpl.class.getName());
	
	 private Connection connection;
	 private PreparedStatement preparedStatement;
	 int generatedId;

	@Override
	public void addpayment(Payment payment) {
		// TODO Auto-generated method stub
	    // Generate a new payment ID
        //String paymentID = CommonUtil.generateIDs(getPaymentIDs());
        
        try {
            // Get database connection using Singleton pattern
            connection = DBConnection.getInstance().getConnection();
            
            // SQL INSERT statement for payments   payment_id,
            String sql = "INSERT INTO payment ( booking_id, card_number, " +
                       "card_holder_name, expiry_date, cvv, amount, payment_date, payment_status) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            // Create prepared statement
            preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            connection.setAutoCommit(false); // Done to avoid having half saved payment details as by default its auto
            
            //Bind parameters
            preparedStatement.setInt(1, payment.getBookingId());
            preparedStatement.setString(2, payment.getCardNumber());
            preparedStatement.setString(3, payment.getCardHolderName());
            preparedStatement.setString(4, payment.getExpiryDate());
            preparedStatement.setString(5, payment.getCvv());
            preparedStatement.setDouble(6, payment.getAmount());
            preparedStatement.setTimestamp(7,Timestamp.valueOf(payment.getPaymentDateTime()));
            preparedStatement.setString(8, payment.getPaymentStatus());
            
            // Execute insert
            preparedStatement.execute();
            connection.commit(); // Commit transaction 
            
            //Get the auto generated key
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1); // Gets the auto-generated ID
                    payment.setPaymentId(generatedId); // Update Payment object with the payment id
                }
            }
            
            log.info("Payment ID " + payment.getPaymentId() + " inserted successfully");
          
           //Catch the errors related to execution of queries and getting the payment id
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error adding payment: " + e.getMessage());
            
            try {//Rollback transaction
                if (connection != null) {//avoid NullPointerException
                    connection.rollback(); // Rollback on error
                    log.log(Level.INFO, "Transaction rolled back successfully");
                }
            } catch (SQLException ex) {//if rollback fails
                log.log(Level.SEVERE, "Error during rollback: " + ex.getMessage());
            }
        }

        finally {
            // Clean up resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.log(Level.SEVERE, "Error closing resources: " + e.getMessage());
            }
        }
	}

	//Update the payment status
	@Override
	public void updatePayment(int paymentID, String status) {
		 try {	// Get database connection using Singleton pattern
		        connection = DBConnection.getInstance().getConnection();
		        
		        //Set the preparedStatement to update payment status
		        preparedStatement = connection.prepareStatement( "UPDATE payment SET payment_status = ? WHERE payment_id = ?" );
	            preparedStatement.setString(1, status);
	            preparedStatement.setInt(2, paymentID);
	            
	            preparedStatement.executeUpdate();
	            //connection.commit(); // Commit transaction
	            
	          // Return true if update was successful
	            
		 }//Catch the errors related to execution of queries
		 catch (SQLException  e) {
		        log.log(Level.SEVERE, "Error updating status: " + e.getMessage());
		    } 
		 finally {
		        // Close resources in reverse order of creation
		        try {
		            if (preparedStatement != null) preparedStatement.close();
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            log.log(Level.SEVERE, "Error closing resources: " + e.getMessage());
		        }
		    }

	}
	
	
	//Remove a payment from the payment table and put into the archives(done using a trigger)
	@Override
	public void removePayment(int paymentID) {
		try {// Get database connection according to Singleton pattern
	        connection = DBConnection.getInstance().getConnection();
	        
	      //Set the preparedStatement to delete a payment from the payment table
	        preparedStatement = connection.prepareStatement("DELETE FROM payment WHERE payment_id = ?");
	        preparedStatement.setInt(1, paymentID);
	        
	        preparedStatement.executeUpdate();
            //connection.commit(); // Commit transaction
		}
		//Catch the errors related to execution of queries
		catch (SQLException  e) {
	        log.log(Level.SEVERE, "Error deleting payment: " + e.getMessage());
	    } 
		
		finally {
	        // Close resources in reverse order of creation
	        try {
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            log.log(Level.SEVERE, "Error closing resources: " + e.getMessage());
	        }
	    }
		
	}
	
	//Delete payments from archives
	public boolean DeletePayment(int paymentID) {
		
		 //Set the preparedStatement to delete a payment from the archivedPayments table
		String sql = "DELETE FROM archivedPayments WHERE payment_id = ?";
		
	    try (Connection conn = DBConnection.getInstance().getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, paymentID);
	        
	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0; // Returns true if deletion succeeded
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	        log.log(Level.SEVERE, "Error deleting payments from the archives: " + e.getMessage());
	        return false;
	    }
		
	}
	
	//get archived payments
	public ArrayList<Payment> getArchivedPayments(int num) {
		return actionOnPayment(-1);
	}
	
	//Get the payment history
	public ArrayList<Payment> getPaymentsById(int id){
		ArrayList<Payment> paymentList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		 try { // Get database connection according to Singleton pattern
		        connection = DBConnection.getInstance().getConnection();
		
		/*CallableStatement callableStatement = connection.prepareCall("{call GetPaymentHistoriica(?)}");*/
		        
		//Call the function to get the payment history       
		CallableStatement callableStatement = connection.prepareCall("{call GetPaymentHistory(?)}");
    	callableStatement.setInt(1, id);
    	
    	resultSet = callableStatement.executeQuery();
    	
    	 //Access results one by one
    	 while (resultSet.next()) {
	            Payment payment = new Payment();
	            payment.setcusId(resultSet.getInt("cust_id"));
	            payment.setcname(resultSet.getString("first_Name"));
	            payment.setPaymentId(resultSet.getInt("payment_id"));
	            payment.setBookingId(resultSet.getInt("booking_id"));
	            payment.setCardNumber(resultSet.getString("card_number"));
	            payment.setCardHolderName(resultSet.getString("card_holder_name"));
	            payment.setExpiryDate(resultSet.getString("expiry_date"));
	            payment.setCvv(resultSet.getString("cvv"));
	            payment.setAmount(resultSet.getDouble("amount"));
	            payment.setPaymentDateTime(resultSet.getTimestamp("payment_date").toLocalDateTime());
	            payment.setPaymentStatus(resultSet.getString("payment_status"));
	            
	            //Add to the list of payment objects
	            paymentList.add(payment);
	        }

	    } catch (SQLException  e) {
	        log.log(Level.SEVERE, "Error retrieving payments: " + e.getMessage());
	    } 
		 
		 finally {
	        // Close resources in reverse order of creation
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            log.log(Level.SEVERE, "Error closing resources: " + e.getMessage());
	        }
	    }
		 
	    return paymentList;
	}
	
	//Get all the payments from the payment table
	@Override
	public ArrayList<Payment> getPayments() {
	    return actionOnPayment(0);
	}
	
	//Get the payments based on the parameters
	private ArrayList<Payment> actionOnPayment(int paymentID) {
	    ArrayList<Payment> paymentList = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    try {
	    	// Get database connection according to Singleton pattern
	        connection = DBConnection.getInstance().getConnection();
	        
	        //Retrieve all the archived payments
	        if (paymentID == -1 ) {
	            preparedStatement = connection.prepareStatement( "SELECT * FROM archivedPayments" );
	            resultSet = preparedStatement.executeQuery();
	          
	            //Retrieve all the payments in the payment table
	        } else if(paymentID == 0){
	            preparedStatement = connection.prepareStatement("SELECT * FROM payment" );
	            resultSet = preparedStatement.executeQuery();
	        }
	        
	      //Access results one by one
	        while (resultSet.next()) {
	            Payment payment = new Payment();
	            payment.setPaymentId(resultSet.getInt("payment_id"));
	            payment.setBookingId(resultSet.getInt("booking_id"));
	            payment.setCardNumber(resultSet.getString("card_number"));
	            payment.setCardHolderName(resultSet.getString("card_holder_name"));
	            payment.setExpiryDate(resultSet.getString("expiry_date"));
	            payment.setCvv(resultSet.getString("cvv"));
	            payment.setAmount(resultSet.getDouble("amount"));
	            payment.setPaymentDateTime(resultSet.getTimestamp("payment_date").toLocalDateTime());
	            payment.setPaymentStatus(resultSet.getString("payment_status"));
	            
	          //Add to the list of payment objects
	            paymentList.add(payment);
	        }

	    } catch (SQLException  e) {
	        log.log(Level.SEVERE, "Error retrieving payments: " + e.getMessage());
	    } 
	    
	    finally {
	        // Close resources in reverse order of creation
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            log.log(Level.SEVERE, "Error closing resources: " + e.getMessage());
	        }
	    }
	    
	    return paymentList;
	}

}
