package servlet.admin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin-bus")
public class AdminBusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * from busDetails");
			ResultSet rs = stmt.executeQuery();
			List <model.admin.AdminBus> buses = new ArrayList <>();
			
			while(rs.next()) {
				
				    model.admin.AdminBus bus = new model.admin.AdminBus();
				
				    bus.setBus_id(rs.getInt("bus_id"));
		            bus.setBus_name(rs.getString("bus_name"));
		            bus.setBus_type(rs.getString("bus_type"));
		            bus.setPrice_per_seat(rs.getDouble("price_per_seat"));
		            bus.setTotal_seats(rs.getInt("total_seats"));
		            bus.setDeparture_time(rs.getTime("departure_time").toLocalTime());
		            bus.setArrival_time(rs.getTime("arrival_time").toLocalTime());
		            bus.setSeats_available(rs.getInt("seats_available"));
		            bus.setIs_active(rs.getBoolean("is_active"));
				    
		            buses.add(bus);
			}
					request.setAttribute("busList", buses);
					request.getRequestDispatcher("/WEB-INF/views/admin/admin-bus.jsp").forward(request, response);
					
		}catch(Exception e) {
			System.err.println("Error fetching bus operators");
			e.printStackTrace();
			
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
