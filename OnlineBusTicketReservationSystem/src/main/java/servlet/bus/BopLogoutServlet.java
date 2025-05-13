package servlet.bus;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/BopLogoutServlet")
public class BopLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session and invalidate it
        HttpSession session = request.getSession(false);  // don't create a new session if it doesn't exist
        if (session != null) {
            session.invalidate();  // Invalidate the session
        }

        // Redirect to the login page
        response.sendRedirect("Homepage.jsp");
    }
}
