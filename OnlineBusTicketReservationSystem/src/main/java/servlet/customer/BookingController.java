package servlet.customer;


import model.customer.Booking;
import util.DBConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/book-seat")
public class BookingController  extends HttpServlet{
	
	   @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		   
		    List<String> bookedseats = new ArrayList<>();
		   
		   try {
			   
			 
			   Connection conn = DBConnection.getInstance().getConnection();
			   
			   String sql = "SELECT seats FROM bookings WHERE bookingStatus = 'Pending'";
			   

			   PreparedStatement stmt = conn.prepareStatement(sql);
			   ResultSet result= stmt.executeQuery();
			   
			   while(result.next()) {
				   String[] seatsArray = result.getString("seats").split(",");
				   for(String seat : seatsArray) {
					   String seatNumber = seat.trim().split("-")[0]; 
				        bookedseats.add(seatNumber);
				   }
			   }
			   request.setAttribute("bookedSeats", bookedseats);
			   request.getRequestDispatcher("/WEB-INF/view/customer/booking.jsp").forward(request, response);

			 
			   
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		   System.out.println("BOOKED SEATS SERVLET DEBUG: " + bookedseats);

		   
	       
	    }

	@Override
      	protected  void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws ServletException, IOException{
	   	try {
	   		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
			int customerId = Integer.parseInt(request.getParameter("customerId"));
			int busId =Integer.parseInt(request.getParameter("busId"));
			String seats = request.getParameter("seats");
			double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
			String travelDate = request.getParameter("travelDate");
			String bookingStatus = request.getParameter("bookingStatus");
			String paymentStatus = request.getParameter("paymentStatus");
			
			
			Booking booking = new Booking();
//			bookingId,customerId,busId,seats,totalPrice,travelDate,bookingStatus,paymentStatus
			
			
			Connection conn = DBConnection.getInstance().getConnection();
			
			String sql = "INSERT INTO bookings (customer_id, bus_id, seats, total_price, travel_date, bookingStatus, paymentStatus) VALUES (?, ?, ?, ?, ?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, booking.getCustomerId());
			stmt.setInt(2, booking.getBusId());
			stmt.setString(3, booking.getSeats());
			stmt.setDouble(4, booking.getTotalPrice());
			stmt.setString(5, booking.getTravelDate());
			stmt.setString(6, booking.getBookingStatus());
			stmt.setString(7, booking.getPaymentStatus());

			int rows = stmt.executeUpdate();
			
			if (rows > 0) {
				request.setAttribute("message", "Booking Successful");
			}else {
				request.setAttribute("message", "Booking Failed.");	
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/customer/booking.jsp");
            rd.forward(request, response);

			
		}catch(Exception e) {
			   e.printStackTrace();
		}
	}

}
