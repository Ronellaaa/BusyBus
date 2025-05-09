package servlet.admin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBConnection;
import model.admin.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin-cus")
public class AdminCusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String sqlQuery = "SELECT cust_id,CONCAT(fist_Name, '  ', last_Name) AS fullName,email,phoneNumber FROM customers";
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sqlQuery);
			ResultSet rs = st.executeQuery();
			
			List<AdminCus> customers = new ArrayList <>();
			
			while(rs.next()) {
				AdminCus cust = new AdminCus();
				cust.setCust_id(rs.getInt("cust_id"));
				cust.setFullName(rs.getString("fullName"));
				cust.setEmail(rs.getString("email"));
				cust.setPhoneNumber(rs.getString("phoneNumber"));
				
				customers.add(cust);
			}
			request.setAttribute("custList", customers);
			request.getRequestDispatcher("/WEB-INF/view/Admin/admin-cus.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
