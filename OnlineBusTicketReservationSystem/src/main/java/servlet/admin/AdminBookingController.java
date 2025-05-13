package servlet.admin;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import util.DBConnection;
import model.admin.AdminCus;
import model.admin.AdminBus;

import model.admin.AdminBooking;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin-booking")

public class AdminBookingController extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String sqlQuery = "SELECT CONCAT(c.first_Name, ' ', c.last_Name) AS fullName, c.phoneNumber,b.booking_id,b.journeyDate,b.seats,b.seatType,bus.bus_type,b.noSeats,b.totalPrice,b.bookingStatus FROM customers c INNER JOIN bookings b ON c.cust_id = b.cust_id INNER JOIN busDetails bus ON b.bus_id = bus.bus_id";
					
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();
			
			List <AdminBooking> bookings = new ArrayList <>();
			
			while(rs.next()) {
				
				AdminBooking booking = new AdminBooking();
				
				booking.setBookingId(rs.getInt("booking_id"));
				booking.setJourneyDate(rs.getObject("journeyDate", LocalDate.class));
				booking.setSeats(rs.getString("seats"));
				booking.setSeatType(rs.getString("seatType"));
			    booking.setBookingStatus(rs.getString("bookingStatus"));
				booking.setNoSeats(rs.getInt("noSeats"));
				booking.setTotalPrice(rs.getDouble("totalPrice"));
				
				AdminCus cust = new AdminCus();
				cust.setFullName(rs.getString("fullName"));
				cust.setPhoneNumber(rs.getString("phoneNumber"));
			    booking.setCust(cust);
				
				AdminBus bus = new AdminBus ();
				bus.setBus_type(rs.getString("bus_type"));
			    booking.setBus(bus);
				
			   
			    
				bookings.add(booking);
				
			}
			request.setAttribute("bookingList", bookings);
			request.getRequestDispatcher("/WEB-INF/view/Admin/admin-booking.jsp").forward(request, response);
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}


