package servlet.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class AuthenticatedServlet
 */
@WebServlet("/AuthenticatedServlet")
public class AuthenticatedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected boolean isLoggedIn(HttpServletRequest req) {
	        HttpSession session = req.getSession(false);
	        return session != null && session.getAttribute("name") != null;
	    }

	    protected void redirectToLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        req.setAttribute("loginError", "Please login first to book your seat.");
	        req.getRequestDispatcher("/WEB-INF/view/customer/SignUp.jsp").forward(req, res);
	    }

}
