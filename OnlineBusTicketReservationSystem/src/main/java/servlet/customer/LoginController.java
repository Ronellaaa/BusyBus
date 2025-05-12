package servlet.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBConnection;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws  ServletException, IOException{
		 req.getRequestDispatcher("/WEB-INF/view/customer/SignUp.jsp").forward(req, res);
		
	}
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws  ServletException, IOException{
		String  email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			String sql = "SELECT * FROM customers WHERE email = ? AND password = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			
			ResultSet rs = stmt.executeQuery();
			
			 if(rs.next()) {
				  HttpSession session = req.getSession();
				  
				  session.setAttribute("customer_id", rs.getInt("cust_id")); 
//				  session.setAttribute("cust_id", rs.getInt("cust_id"));
				  session.setAttribute("name", rs.getString("first_Name") + " " + rs.getString("last_Name")); 
			        session.setAttribute("email", rs.getString("email"));
			        session.setAttribute("phone", rs.getString("phoneNumber"));
//				  session.setAttribute("password", rs.getInt("password"));
			        
				  res.sendRedirect("Homepage.jsp");
			 }else {
	                req.setAttribute("error", "Invalid email or password");
	                req.getRequestDispatcher("/WEB-INF/view/customer/SignUp.jsp").forward(req, res);
	            }
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
	

}
