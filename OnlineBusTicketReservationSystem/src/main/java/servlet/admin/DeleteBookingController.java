package servlet.admin;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import util.DBConnection;


@WebServlet("/admin-delete-booking")
public class DeleteBookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   int id = Integer.parseInt(request.getParameter("id"));

	        try {
	            Connection conn = DBConnection.getInstance().getConnection();
	           
	            
	            PreparedStatement deletePayment = conn.prepareStatement("DELETE FROM payment WHERE booking_id = ?");
	            deletePayment.setInt(1, id);
	            deletePayment.executeUpdate();

	          
	            PreparedStatement deleteBooking = conn.prepareStatement("DELETE FROM bookings WHERE booking_id = ?");
	            deleteBooking.setInt(1, id);
	            deleteBooking.executeUpdate();
	            
	            response.sendRedirect(request.getContextPath() + "/admin-booking");
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.getWriter().println("Error deleting booking.");
	        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
