package servlet.customer;


import model.customer.Booking;
import util.DBConnection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;



@WebServlet("/viewbookings")
public class ViewBookingsController extends HttpServlet {
	
	 private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
		 response.getWriter().println("Servlet is working!");
		
		 
		 
       
        try {
        	  List<Booking> bookingList = new ArrayList<>();
        	  
            Connection conn = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM bookings";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
          

            while (rs.next()) {
            	 Booking booking = new Booking();
            	 booking.setBookingId(rs.getInt("booking_id"));
                 booking.setCustomerId(rs.getInt("customer_id"));
                 booking.setBusId(rs.getInt("bus_id"));
                 booking.setSeats(rs.getString("seats"));
                 booking.setTotalPrice(rs.getDouble("total_price"));
                 booking.setJourneyDate(rs.getString("journeyDate"));
                 booking.setBookingStatus(rs.getString("bookingStatus"));
                 booking.setPaymentStatus(rs.getString("paymentStatus"));
                 bookingList.add(booking);
            }
            System.out.println("Booking list size: " + bookingList.size());
            request.setAttribute("bookingList", bookingList);
            request.getRequestDispatcher("/WEB-INF/view/customer/view-bookings.jsp").forward(request, response);
            

           

        } catch (Exception e) {
        	 response.getWriter().write("Error: " + e.getMessage());
            e.printStackTrace();
        }
        
     
        
    }
}
