package servlet.customer;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bus.Bus;
import model.customer.*;
import util.DBConnection;


@WebServlet("/profile")
public class ProfileController  extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws  ServletException, IOException{
		

		 
		 try {
			 
				HttpSession session = req.getSession(false); // false means do not create if not exists

				Object custObj = session != null ? session.getAttribute("customer_id") : null;

				if (custObj == null) {
					res.sendRedirect("login.jsp");
					return;
				}

				int customerId = (int) custObj;

			 
			 List<Customer> customerList = new ArrayList<>();
			  Connection conn = DBConnection.getInstance().getConnection();
			 
			  String sql =  "SELECT * FROM customers WHERE cust_id = ?";
			 

			  PreparedStatement stmt = conn.prepareStatement(sql);
			  stmt.setInt(1, customerId);
			  ResultSet rs = stmt.executeQuery();
			  
			  while(rs.next()) {
				  Customer customer = new Customer();
				  customer.setFirst_Name(rs.getString("first_Name"));
				  customer.setLast_Name(rs.getString("last_Name"));
				  customer.setEmail(rs.getString("email"));
				  customer.setPhoneNumber(rs.getString("phoneNumber")); // ✅ Correct column name

				  customer.setPassword(rs.getString("password"));
				  customerList.add(customer);
				  
				  session.setAttribute("name", customer.getFirst_Name() + " " + customer.getLast_Name());
	                session.setAttribute("email", customer.getEmail());
	                session.setAttribute("phone", rs.getString("phoneNumber")); // ✅ Consistent


			  }
			  req.setAttribute("CustomerList", customerList);
			  
			  
			// ✅ Fetch booked tickets with route + operator
	            List<Booking> bookingList = new ArrayList<>();
	            String bookingSql = """
	                SELECT b.booking_id, b.journeyDate, b.seats, b.bookingStatus,
	                       r.busRouteName, d.bus_name,d.bus_type
	                FROM bookings b
	                JOIN busDetails d ON b.bus_id = d.bus_id
	                JOIN busRouteAssignment a ON b.bus_id = a.bus_id
	                 JOIN BusRoute r ON a.route_id = r.busRouteId
	                WHERE b.cust_id = ?
	            """;
	            

	            PreparedStatement bookStmt = conn.prepareStatement(bookingSql);
	            bookStmt.setInt(1, customerId);
	            ResultSet bookingRs = bookStmt.executeQuery();
	           
			  

	            while (bookingRs.next()) {
	                Booking b = new Booking();
	                Bus bus = new Bus();
	                
	                b.setBookingId(bookingRs.getInt("booking_id"));
	                b.setJourneyDate(bookingRs.getString("journeyDate"));
	                b.setSeats(bookingRs.getString("seats"));
	                b.setBookingStatus(bookingRs.getString("bookingStatus"));
	                b.setBusRouteName(bookingRs.getString("busRouteName"));
	                bus.setBusName(bookingRs.getString("bus_name"));
					bus.setBusType(bookingRs.getString("bus_type"));
				    b.setBus(bus);
					   
					  
	             
	              
	                bookingList.add(b);
	            }

	            req.setAttribute("bookingList", bookingList);

			  
			  
			  
	            req.getRequestDispatcher("/WEB-INF/view/customer/profile.jsp").forward(req, res);
	            

			  
		 }catch(Exception e) {
			 e.printStackTrace();
				res.getWriter().write("Something went wrong: " + e.getMessage());
		 }
		 
		 
	}
	
	


	
	}
