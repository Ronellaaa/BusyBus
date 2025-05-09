package servlet.admin;
import java.sql.*;
import util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin-delete-customer")
public class AdminDeleteCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int id = Integer.parseInt(request.getParameter("id"));

	        try {
	            Connection conn = DBConnection.getInstance().getConnection();
	           
	            
	            PreparedStatement deleteCustomer = conn.prepareStatement("DELETE FROM customers WHERE cust_id = ?");
	            deleteCustomer.setInt(1, id);
	            deleteCustomer.executeUpdate();


	            response.sendRedirect(request.getContextPath() + "/admin-cus");
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.getWriter().println("Error deleting customers.");
	        }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
