package model.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import model.payment.Payment;
import util.DBConnection;
//import com.oop.util.DBConnectionUtil;

public class Refunded extends Status{
	
	public Refunded() {
		super();
	}
	
	//Get payments by the status
	@Override
	public ArrayList<Payment> getPaymentsByStatus(String type) {
		
		ArrayList<Payment> paymentList = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    try {
	        connection = DBConnection.getInstance().getConnection();
	        
	        preparedStatement = connection.prepareStatement("SELECT * FROM payment WHERE payment_status = 'REFUNDED'" );
            resultSet = preparedStatement.executeQuery();
            
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
	            
	            paymentList.add(payment);
	        }

	    } catch (SQLException  e) {
	        log.log(Level.SEVERE, "Error retrieving payments: " + e.getMessage());
	    } finally {
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
