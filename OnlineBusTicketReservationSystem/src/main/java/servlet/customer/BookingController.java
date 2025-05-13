package servlet.customer;


import model.bus.Bus;
import model.customer.Booking;
import util.DBConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import service.bus.BusServiceImpl;
import service.bus.IBusService;

@WebServlet("/book-seat")
public class BookingController  extends HttpServlet{
	
	   @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		   
		   HttpSession session = request.getSession(false); 
		    if (session == null || session.getAttribute("name") == null) {
		        response.sendRedirect("login.jsp"); 
		        return;
		    }

		   
		    
		    String busIdStr = request.getParameter("bus_id");
		    if (busIdStr == null || busIdStr.isEmpty()) {
		        response.sendRedirect("BusCardsServlet?errorMessage=Bus+ID+is+missing");
		        return;
		    }

		    int busId = Integer.parseInt(busIdStr);

		    
		   
	

		   List<Booking> bookings = new ArrayList<>();
		   List<String> bookedseats = new ArrayList<>();
		   
		   double pricePerSeat = 1000.0;
	        int totalSeats = 50;
	        String routeName = "";
	        
	        String busName = "";
		   
		   try {
			   
			 
			   Connection conn = DBConnection.getInstance().getConnection();
			   
//			   String sql = "SELECT seats FROM bookings WHERE bookingStatus = 'Pending'";
			   String sql = "SELECT seats FROM bookings WHERE bookingStatus = 'Pending' AND bus_id = ?";
			   String bussql = "SELECT * FROM busDetails WHERE bus_id = ?" ;

			   PreparedStatement stmt = conn.prepareStatement(sql);
			   stmt.setInt(1, busId);
			   
			   PreparedStatement busStmt = conn.prepareStatement(bussql);
			   busStmt.setInt(1, busId);
			   
			   ResultSet result= stmt.executeQuery();
			   ResultSet busResult= busStmt.executeQuery();
			   
			   while(result.next()) {
				   String[] seatsArray = result.getString("seats").split(",");
				   for(String seat : seatsArray) {
					   String seatNumber = seat.trim().split("-")[0]; 
				        bookedseats.add(seatNumber);
				   }
			   }
			   
			   while(busResult.next()) {
				   Bus bus = new Bus();
				   
				   Booking booking = new Booking();
				   bus.setBusName(busResult.getString("bus_name"));
				   bus.setBusType(busResult.getString("bus_type"));
				   
				   booking.setBus(bus);
				   
				   bookings.add(booking);
			   }
			   
			   // === FETCH BUS DETAILS + ROUTE INFO ===
			   
			   
			   String busInfo = """
					    SELECT b.price_per_seat, b.total_seats, b.bus_name, r.busRouteName
					    FROM busDetails b
					    JOIN busRouteAssignment a ON a.bus_id = b.bus_id
					    JOIN BusRoute r ON r.busRouteId = a.route_id
					    WHERE b.bus_id = ?
					""";

			   
			   PreparedStatement busstmt = conn.prepareStatement(busInfo);
			   busstmt.setInt(1, busId);
			   
			   ResultSet busRs= busstmt.executeQuery();
			   
			   
			   if(busRs.next()) {
				   pricePerSeat = busRs.getDouble("price_per_seat");
	                totalSeats = busRs.getInt("total_seats");
	                busName = busRs.getString("bus_name");
	                routeName = busRs.getString("busRouteName");
	                
			   }
			   
			   
			   
//			   request.setAttribute("bookedSeats", bookedseats);
			  
			 
			   
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		   System.out.println("BOOKED SEATS SERVLET DEBUG: " + bookedseats);

		   request.setAttribute("bookedSeats", bookedseats);
	        request.setAttribute("busId", busId);
	        request.setAttribute("pricePerSeat", pricePerSeat);
	        request.setAttribute("totalSeats", totalSeats);
	        request.setAttribute("routeName", routeName);
	        request.setAttribute("busName", busName);
	        request.setAttribute("bookings", bookings);
	        
	        
	        request.getRequestDispatcher("/WEB-INF/view/customer/bookingSeats.jsp").forward(request, response);

	       
	    }

	@Override
      	protected  void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws ServletException, IOException{
		  try {
		        int customerId = Integer.parseInt(request.getParameter("customerId"));
		        int busId = Integer.parseInt(request.getParameter("busId"));
		        String seats = request.getParameter("seats");
		        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
		        String journeyDate = request.getParameter("journeyDate");
		        String seatType = request.getParameter("seatType");
		        int seatCount = Integer.parseInt(request.getParameter("seatCount"));

		        // Set default status values
		        String bookingStatus = "PENDING";
		        String paymentStatus = "PENDING";

		        Booking booking = new Booking();

		        Connection conn = DBConnection.getInstance().getConnection();

		        String sql = "INSERT INTO bookings (cust_id, bus_id, journeyDate, seats, seatType, bookingStatus, paymentStatus, totalPrice, noSeats) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		        PreparedStatement stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
		        stmt.setInt(1, customerId);
		        stmt.setInt(2, busId);
		        stmt.setString(3, journeyDate);
		        stmt.setString(4, seats);
		        stmt.setString(5, seatType);
		        stmt.setString(6, bookingStatus);
		        stmt.setString(7, paymentStatus);
		        stmt.setDouble(8, totalPrice);
		        stmt.setInt(9, seatCount);

		        int rows = stmt.executeUpdate();

		        if (rows > 0) {
		            ResultSet rs = stmt.getGeneratedKeys();
		            if (rs.next()) {
		                int bookingId = rs.getInt(1);
		                booking.setBookingId(bookingId); // Optional: keep in model

		                // Redirect to payment form with bookingId and totalPrice
		                response.sendRedirect("getPaymentFormServlet?booking_id=" + bookingId + "&totalPrice=" + totalPrice);
		                return;
		            }
		        }

		        // If failed
		        request.setAttribute("message", "Booking Failed.");
		        request.getRequestDispatcher("/WEB-INF/view/customer/bookingSeats.jsp").forward(request, response);

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}

}
