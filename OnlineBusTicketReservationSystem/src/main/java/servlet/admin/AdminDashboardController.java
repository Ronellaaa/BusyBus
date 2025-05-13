package servlet.admin;



import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.DBConnection;
import model.admin.AdminRoutes;
import model.admin.AdminBooking;
import model.admin.AdminBus;
import model.admin.AdminCus;
import model.admin.AdminAddTicket;
import model.admin.AdminDashboard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin")
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String recentBus = "Select bus_name,bus_type,total_seats,is_active from busDetails ";
		
		String recentRoutes = "SELECT b.bus_name, b.bus_type, b.departure_time, b.arrival_time, b.total_seats, r.busRouteName " +
                "FROM busDetails b " +
                "INNER JOIN busRouteAssignment a ON b.bus_id = a.bus_id " +
                "INNER JOIN BusRoute r ON r.busRouteId = a.route_id";
		
		
		String displayTicketsQuery = "SELECT CONCAT(c.first_Name, ' ', c.last_Name) AS fullName, " +
			    "b.bus_name, b.bus_type, r.busRouteName, " +
			    "bo.journeyDate, a.assignment_id, " + 
			    "GROUP_CONCAT(bo.seats ORDER BY bo.seats ASC) AS seatNumbers, " +
			    "SUM(bo.totalPrice) AS totalAmount, " + 
			    "MAX(p.payment_date) AS paymentDate " +  
			    "FROM customers c " +
			    "INNER JOIN bookings bo ON c.cust_id = bo.cust_id " +
			    "INNER JOIN busDetails b ON bo.bus_id = b.bus_id " +
			    "INNER JOIN payment p ON bo.booking_id = p.booking_id " +
			    "INNER JOIN busRouteAssignment a ON a.bus_id = b.bus_id " +
			    "INNER JOIN BusRoute r ON r.busRouteId = a.route_id " +
			    "WHERE p.payment_status = 'COMPLETED' " +
			    "GROUP BY c.cust_id, b.bus_id, r.busRouteId, bo.journeyDate, a.assignment_id " +
			    "ORDER BY paymentDate DESC " +
			    "LIMIT 5";


		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(displayTicketsQuery);
			ResultSet rs = stmt.executeQuery();
			
			List<AdminDashboard> adminTickets = new ArrayList <>();
			
			while(rs.next()) {
				
				AdminDashboard dashboard = new AdminDashboard();
				
				AdminCus cust = new AdminCus();
				cust.setFullName(rs.getString("fullName"));
				dashboard.setCust(cust);
				
				AdminBus bus = new AdminBus();
				bus.setBus_name(rs.getString("bus_name"));
		        bus.setBus_type(rs.getString("bus_type"));
		        dashboard.setBus(bus);
		        
				AdminBooking booking = new AdminBooking ();
				booking.setSeats(rs.getString("seatNumbers"));
				booking.setTotalPrice(rs.getDouble("totalAmount"));
				booking.setJourneyDate(rs.getObject("journeyDate", LocalDate.class));
				dashboard.setBooking(booking);	
				
				AdminRoutes routes = new AdminRoutes();
				routes.setRouteName(rs.getString("busRouteName"));
				dashboard.setRoutes(routes);
				
				AdminAddTicket ticket = new AdminAddTicket ();
				ticket.setRoute(routes);
				adminTickets.add(dashboard);
				
			}
			

			PreparedStatement busStmt = conn.prepareStatement(recentBus);
			ResultSet busRs = busStmt.executeQuery();
			List<AdminDashboard> adminBus = new ArrayList <>();
			Set<String> busName = new HashSet<>();
		    Set<String> busTypes = new HashSet<>();
		    Set<String> busStatuses = new HashSet<>();
			
			while(busRs.next()) {
				AdminDashboard dashboard = new AdminDashboard();
				
				AdminBus bus = new AdminBus();
				
				bus.setBus_name(busRs.getString("bus_name"));
		        bus.setBus_type(busRs.getString("bus_type"));
		        bus.setTotal_seats(busRs.getInt("total_seats"));	      
		        bus.setIs_active(busRs.getBoolean("is_active"));
		        
		        busTypes.add(busRs.getString("bus_type"));
	            busName.add(busRs.getString("bus_name"));
	           
	          
		        dashboard.setBus(bus);
		        adminBus.add(dashboard);
		        
			}
			
			PreparedStatement routeStmt = conn.prepareStatement(recentRoutes);
			ResultSet routeRs = routeStmt.executeQuery();
			List<AdminDashboard> adminRoutes = new ArrayList <>();
			
			while(routeRs.next()) {
				AdminDashboard dashboard = new AdminDashboard();

				AdminBus bus = new AdminBus();
				bus.setBus_name(routeRs.getString("bus_name"));
		        bus.setBus_type(routeRs.getString("bus_type"));
		        bus.setDeparture_time(routeRs.getTime("departure_time").toLocalTime());
	            bus.setArrival_time(routeRs.getTime("arrival_time").toLocalTime());
		        bus.setTotal_seats(routeRs.getInt("total_seats"));	      
		   
		        dashboard.setBus(bus);
		        
		        AdminRoutes routes = new AdminRoutes();
				routes.setRouteName(routeRs.getString("busRouteName"));
				dashboard.setRoutes(routes);
				
				adminRoutes.add(dashboard);
		        
			}
			
			PreparedStatement totalCustomersStmt = conn.prepareStatement("SELECT COUNT(*) AS totalCustomers FROM customers");
			ResultSet customerRs = totalCustomersStmt.executeQuery();
			if (customerRs.next()) {
			    request.setAttribute("totalCustomers", customerRs.getInt("totalCustomers"));
			}

			PreparedStatement totalBusStmt = conn.prepareStatement("SELECT COUNT(*) AS totalBusOperators FROM busDetails");
			ResultSet busOpRs = totalBusStmt.executeQuery();
			if (busOpRs.next()) {
			    request.setAttribute("totalBusOperators", busOpRs.getInt("totalBusOperators"));
			}

			PreparedStatement totalSeatsStmt = conn.prepareStatement("SELECT SUM(total_seats) AS totalSeats FROM busDetails");
			ResultSet seatsRs = totalSeatsStmt.executeQuery();
			if (seatsRs.next()) {
			    request.setAttribute("totalSeats", seatsRs.getInt("totalSeats"));
			}

			PreparedStatement totalPaymentStmt = conn.prepareStatement("SELECT SUM(amount) AS totalPayments FROM payment WHERE payment_status = 'COMPLETED'");
			ResultSet paymentRs = totalPaymentStmt.executeQuery();
			if (paymentRs.next()) {
			    request.setAttribute("totalPayments", paymentRs.getDouble("totalPayments"));
			}

			
			
			
			request.setAttribute("adminTickets", adminTickets);
			request.setAttribute("adminBus", adminBus);
			
			request.setAttribute("adminRoutes", adminRoutes);
			
			request.getRequestDispatcher("/WEB-INF/view/Admin/admin.jsp").forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
