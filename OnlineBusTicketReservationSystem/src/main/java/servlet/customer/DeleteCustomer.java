package servlet.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBConnection;

@WebServlet("/deleteCustomer")

public class DeleteCustomer  extends  HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 HttpSession session = req.getSession(false);
		  if (session == null || session.getAttribute("customer_id") == null) {
		        res.sendRedirect("Homepage.jsp");
		        return;
		    }
		  
		  int customerId = (int) session.getAttribute("customer_id");
		  
		  try {
			  Connection conn = DBConnection.getInstance().getConnection();
			  String sql = "DELETE FROM customers WHERE cust_id = ?";
			  PreparedStatement stmt = conn.prepareStatement(sql);
			  stmt.setInt(1, customerId); 
			  int rows = stmt.executeUpdate();
			  
			  if(rows > 0) {
				  session.invalidate(); // Log the user out
	                res.sendRedirect("Homepage.jsp"); // or login.jsp
			  }else {
	                res.getWriter().println("❌ Deletion failed. Try again.");
	            }
			  
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		
	}
}
