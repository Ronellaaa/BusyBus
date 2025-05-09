package servlet.admin;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBConnection;
import model.admin.AdminRoutes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/admin-add-routes", "/admin-view-routes", "/admin-edit-routes", "/admin-delete-routes"})

public class AdminRoutesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();
		
		if("/admin-add-routes".equals(path)) {
			 try {
			        Connection conn = DBConnection.getInstance().getConnection();

			        String getMaxIdQuery = "SELECT MAX(busRouteId) AS maxId FROM BusRoute";
			        PreparedStatement stmt = conn.prepareStatement(getMaxIdQuery);
			        ResultSet rs = stmt.executeQuery();

			        int nextRouteId = 1; 
			        if (rs.next()) {
			            nextRouteId = rs.getInt("maxId") + 1;
			        }

			        request.setAttribute("nextRouteId", nextRouteId);
			        request.getRequestDispatcher("/WEB-INF/view/Admin/admin-add-routes.jsp").forward(request, response);

			    } catch (Exception e) {
			        e.printStackTrace();
			        response.getWriter().println("Error fetching next Route ID.");
			    }
			
		}else if("/admin-edit-routes".equals(path)){
			int id = Integer.parseInt(request.getParameter("id"));
			
			String getUpdateQuery = "SELECT busRouteId, busRouteName FROM BusRoute WHERE busRouteId = ?";

			
			Connection conn = DBConnection.getInstance().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(getUpdateQuery);
				stmt.setInt(1,id);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()) {
					AdminRoutes route = new AdminRoutes();
			        route.setRouteId(rs.getInt("busRouteId"));
			        route.setRouteName(rs.getString("busRouteName"));
			        request.setAttribute("route", route);
			        request.getRequestDispatcher("/WEB-INF/view/Admin/admin-edit-routes.jsp").forward(request, response);
			    
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}else if("/admin-delete-routes".equals(path)){
			int id = Integer.parseInt(request.getParameter("id"));
			String deleteQuery = "DELETE FROM BusRoute WHERE busRouteId = ?";
			Connection conn = DBConnection.getInstance().getConnection();
			
			try {
				PreparedStatement stmt = conn.prepareStatement(deleteQuery);
				stmt.setInt(1,id);
				stmt.executeUpdate();
				
				 response.sendRedirect(request.getContextPath() + "/admin-view-routes");
				 
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			
		try {
			String sqlQuery = "SELECT * from BusRoute";
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();
			
			List <AdminRoutes> routes = new ArrayList <>();
			Map<Integer, String> routeMap = new HashMap<>();
			
			while(rs.next()) {
				AdminRoutes route = new AdminRoutes ();
				route.setRouteId(rs.getInt("busRouteId"));
				route.setRouteName(rs.getString("busRouteName"));
				routeMap.put(rs.getInt("busRouteId"),rs.getString("busRouteName"));
				
				routes.add(route);
				
			}
			request.setAttribute("routeList",routes);
			request.getRequestDispatcher("/WEB-INF/view/Admin/admin-view-routes.jsp").forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();		}
		
	}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		if("/admin-add-routes".equals(path)) {
			
		try {
			String routeName = request.getParameter("route-name");
			
			String sqlQuery = "INSERT INTO BusRoute (busRouteName) Values (?) ";		   
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlQuery);
			
			stmt.setString(1, routeName);
			int rowsInserted = stmt.executeUpdate();
			
			if(rowsInserted > 0) {
				System.out.println("Route inserted successfully!");
			}

			
		}catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/admin-view-routes");

	 }else if("/admin-edit-routes".equals(path)) {
		 
		 int id = Integer.parseInt(request.getParameter("id"));
		 String routeName = request.getParameter("route-name");
		 Connection conn = DBConnection.getInstance().getConnection();
		 String updateQuery = "UPDATE BusRoute SET busRouteName = ? WHERE busRouteId = ?";
		 
		 try {
			 PreparedStatement stmt = conn.prepareStatement(updateQuery);
			 stmt.setString(1, routeName);		 
			 stmt.setInt(2, id);
			 stmt.executeUpdate();
			 
			 response.sendRedirect(request.getContextPath() + "/admin-view-routes");
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 
	 }
	}
}
