package servlet.admin;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import util.DBConnection;
import model.admin.AdminBooking;
import model.admin.AdminCus;
import model.admin.AdminPayments;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin-payments")
public class AdminPaymentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String sqlQuery = "SELECT p.payment_id, " +
	                  "CONCAT(c.fist_Name, ' ', c.last_Name) AS fullName, " +
	                  "b.booking_id, p.amount, p.payment_date, p.payment_status " +
	                  "FROM customers c " +
	                  "INNER JOIN bookings b ON c.cust_id = b.cust_id " +
	                  "INNER JOIN payment p ON b.booking_id = p.booking_id";

			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();
			
			List<AdminPayments> payments = new ArrayList <>();
			
			while (rs.next()) {
				
				AdminPayments payment = new AdminPayments();
				
				payment.setPaymentId(rs.getInt("payment_id"));
				payment.setAmount(rs.getDouble("amount"));
				payment.setPaymentDate(rs.getObject("payment_date",LocalDate.class));
				payment.setPaymentStatus(rs.getString("payment_status"));
				
				AdminCus cust = new AdminCus();
				cust.setFullName(rs.getString("fullName"));
				payment.setCust(cust); 
				
				AdminBooking booking = new AdminBooking ();
				booking.setBookingId(rs.getInt("booking_id"));
				payment.setBooking(booking);
				
				payments.add(payment);
				
			}
			request.setAttribute("paymentsList", payments);
			request.getRequestDispatcher("/WEB-INF/view/Admin/admin-payments.jsp").forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
