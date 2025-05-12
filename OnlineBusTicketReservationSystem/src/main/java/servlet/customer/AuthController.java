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
import model.customer.Customer;



@WebServlet("/sign-up")

public class AuthController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    // If someone tries to access /sign-up directly via URL, redirect them
	    req.getRequestDispatcher("/WEB-INF/view/customer/SignUp.jsp").forward(req, res);
	}

	
	protected  void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		String firstName = req.getParameter("first_Name");
		String lastName = req.getParameter("last_Name");
		String  email = req.getParameter("email");
		String phone= req.getParameter("phoneNumber");
		String password = req.getParameter("password");
		
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			
			String sql = "INSERT INTO customers (first_Name, last_Name, email, phoneNumber, password) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, email);
			stmt.setString(4, phone);
			stmt.setString(5, password);
			
			int rows = stmt.executeUpdate();
			
			if(rows > 0) {
				String fetchSql = "SELECT * FROM customers WHERE email=? AND password=?";
				PreparedStatement fetchstmt = conn.prepareStatement(fetchSql);
				
				fetchstmt.setString(1, email);
				fetchstmt.setString(2, password);
				
				  ResultSet rs = fetchstmt.executeQuery();
				  
				  if(rs.next()) {
					  HttpSession session = req.getSession();
					  session.setAttribute("cust_id", rs.getInt("cust_id"));
//					  session.setAttribute("cust_id", rs.getInt("cust_id"));
				        session.setAttribute("first_Name", rs.getString("first_Name"));
				        session.setAttribute("last_Name", rs.getString("last_Name"));
				        session.setAttribute("email", rs.getString("email"));
				        session.setAttribute("phoneNumber", rs.getString("phoneNumber"));
					  session.setAttribute("password", rs.getInt("password"));
				        
					  res.sendRedirect("Homepage.jsp");

					  
				  }else {
					  req.setAttribute("error", "fetch"); // or "insert"
					  req.getRequestDispatcher("/WEB-INF/view/customer/SignUp.jsp").forward(req, res);

				  }
				
			}else {
				req.setAttribute("error", "fetch"); // or "insert"
				req.getRequestDispatcher("/WEB-INF/view/customer/SignUp.jsp").forward(req, res);

			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}	  
		
	}

	
}
