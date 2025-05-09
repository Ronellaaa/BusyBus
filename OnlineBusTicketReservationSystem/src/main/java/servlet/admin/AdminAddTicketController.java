package servlet.admin;

import java.sql.*;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;

import java.util.Set;

import util.DBConnection;

import model.admin.AdminBus;
import model.admin.AdminRoutes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/admin-add-ticket")
public class AdminAddTicketController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private void loadFormData (HttpServletRequest request) throws ServletException, IOException {
		 try {
		        Connection conn = DBConnection.getInstance().getConnection();

		       
		        String busQuery = "SELECT * FROM busDetails";
		        PreparedStatement busStmt = conn.prepareStatement(busQuery);
		        ResultSet busRs = busStmt.executeQuery();
		        
		        List<AdminBus> busList = new ArrayList<>();
		        Set<String> busName = new HashSet<>();
		        Set<String> busTypes = new HashSet<>();
		        Set<String> busStatuses = new HashSet<>();
		        
		        while (busRs.next()) {
		            AdminBus bus = new AdminBus();
		            bus.setBus_name(busRs.getString("bus_name"));
		            bus.setBus_type(busRs.getString("bus_type"));
		            bus.setPrice_per_seat(busRs.getDouble("price_per_seat"));
		            bus.setTotal_seats(busRs.getInt("total_seats"));
		            
		            busTypes.add(busRs.getString("bus_type"));
		            busName.add(busRs.getString("bus_name"));
		            busStatuses.add(busRs.getBoolean("is_active") ? "Active" : "Inactive");
		            busList.add(bus);
		        }

		      
		        String routeQuery = "SELECT * FROM BusRoute";
		        PreparedStatement routeStmt = conn.prepareStatement(routeQuery);
		        ResultSet routeRs = routeStmt.executeQuery();
		        List<AdminRoutes> routeList = new ArrayList<>();
		        
		        
		        while (routeRs.next()) {
		            AdminRoutes route = new AdminRoutes();
		            route.setRouteName(routeRs.getString("busRouteName"));
		            routeList.add(route);
		        }

		        request.setAttribute("busList", busList);
		        request.setAttribute("busTypes", busTypes);
		        request.setAttribute("busName", busName);
		        request.setAttribute("busStatuses", busStatuses);
		        request.setAttribute("routeList", routeList);
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		
	}
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     loadFormData(request);
     request.getRequestDispatcher("/WEB-INF/view/Admin/admin-add-ticket.jsp").forward(request, response);
		
	   
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        String busName = request.getParameter("busname");
	        String busType = request.getParameter("bustype");
	        double price = Double.parseDouble(request.getParameter("price"));
	        boolean isActive = request.getParameter("status").equalsIgnoreCase("active");
	        String routeName = request.getParameter("route");

	        String sqlQuery = "INSERT INTO busRouteAssignment (bus_id, route_id) "
	                        + "SELECT b.bus_id, r.busRouteId "
	                        + "FROM busDetails b, BusRoute r "
	                        + "WHERE b.bus_name = ? AND b.bus_type = ? AND b.price_per_seat = ? "
	                        + "AND b.is_active = ? AND r.busRouteName = ?";

	        Connection conn = DBConnection.getInstance().getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sqlQuery);

	        stmt.setString(1, busName);
	        stmt.setString(2, busType);
	        stmt.setDouble(3, price);
	        stmt.setBoolean(4, isActive);
	        stmt.setString(5, routeName);

	        int rowsInserted = stmt.executeUpdate();

	        if (rowsInserted > 0) {
	            request.setAttribute("successMessage", "Bus assigned to route successfully!");
	        } else {
	            request.setAttribute("errorMessage", "Assignment failed. Please check input values.");
	        }
	        
	        loadFormData(request);
	        request.getRequestDispatcher("/WEB-INF/view/Admin/admin-add-ticket.jsp").forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "An error occurred while assigning the bus to route.");
	        request.getRequestDispatcher("/WEB-INF/view/Admin/admin-add-ticket.jsp").forward(request, response);
	    }
	}

}
