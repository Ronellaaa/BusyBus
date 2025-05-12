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

@WebServlet("/UpdateCustomer")
public class UpdateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/customer/update.jsp").forward(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    HttpSession session = req.getSession(false);
	    if (session == null || session.getAttribute("customer_id") == null) {
	        res.sendRedirect("login.jsp");
	        return;
	    }

	    int customerId = (int) session.getAttribute("customer_id");

	    String firstName = req.getParameter("firstName");
	    String lastName = req.getParameter("lastName");
	    String email = req.getParameter("email");
	    String phone = req.getParameter("phone");
	    String password = req.getParameter("password"); 
	    
	    
	    try {
	        Connection conn = DBConnection.getInstance().getConnection();

	        PreparedStatement stmt;
	        String sql;

	        if (password != null && !password.trim().isEmpty()) {
	         
	            sql = "UPDATE customers SET first_Name = ?, last_Name = ?, email = ?, phoneNumber = ?, password = ? WHERE cust_id = ?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, firstName);
	            stmt.setString(2, lastName);
	            stmt.setString(3, email);
	            stmt.setString(4, phone);
	            stmt.setString(5, password);
	            stmt.setInt(6, customerId);
	        } else {
	           
	            sql = "UPDATE customers SET first_Name = ?, last_Name = ?, email = ?, phoneNumber = ? WHERE cust_id = ?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, firstName);
	            stmt.setString(2, lastName);
	            stmt.setString(3, email);
	            stmt.setString(4, phone);
	            stmt.setInt(5, customerId);
	        }

	        int rows = stmt.executeUpdate();
	        if (rows > 0) {
	            session.setAttribute("name", firstName + " " + lastName);
	            session.setAttribute("email", email);
	            session.setAttribute("phone", phone);
	        }

	        res.sendRedirect("profile");

	    } catch (Exception e) {
	        e.printStackTrace();
	        res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    }
	}

}
