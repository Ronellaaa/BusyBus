package servlet.admin;
import java.sql.*;



import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import util.DBConnection;

@WebServlet("/admin-delete-bus")
public class AdminDeleteBusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			 int busId= Integer.parseInt(request.getParameter("id"));

		        try {
		        	   Connection conn = DBConnection.getInstance().getConnection();
		        	   
		        	   PreparedStatement getBookings = conn.prepareStatement("SELECT booking_id FROM bookings WHERE bus_id = ?");
		               getBookings.setInt(1, busId);
		               ResultSet rs = getBookings.executeQuery();

		            
		               while (rs.next()) {
		                   int bookingId = rs.getInt("booking_id");

		                   PreparedStatement deletePayment = conn.prepareStatement("DELETE FROM payment WHERE booking_id = ?");
		                   deletePayment.setInt(1, bookingId);
		                   deletePayment.executeUpdate();
		               }
		               
		               PreparedStatement deleteRouteAssignment = conn.prepareStatement("DELETE FROM busrouteassignment WHERE bus_id = ?");
		               deleteRouteAssignment.setInt(1, busId);
		               deleteRouteAssignment.executeUpdate();
		              
		               PreparedStatement deleteBooking = conn.prepareStatement("DELETE FROM bookings WHERE bus_id = ?");
		               deleteBooking.setInt(1, busId);
		               deleteBooking.executeUpdate();

		               PreparedStatement deleteBus = conn.prepareStatement("DELETE FROM busDetails WHERE bus_id = ?");
		               deleteBus.setInt(1, busId);
		               deleteBus.executeUpdate();


		            response.sendRedirect(request.getContextPath() + "/admin-bus");
		        } catch (Exception e) {
		            e.printStackTrace();
		            response.getWriter().println("Error deleting buses.");
		        }
		}
		
		
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
