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

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/customer/SignUp.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        try {
            String redirectPage = "";

            // CUSTOMER: validate using DB
            if ("customer".equals(role)) {
                Connection conn = DBConnection.getInstance().getConnection();
                String sql = "SELECT * FROM customers WHERE email = ? AND password = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    HttpSession session = req.getSession();
                    session.setAttribute("customer_id", rs.getInt("cust_id"));
                    session.setAttribute("name", rs.getString("first_Name") + " " + rs.getString("last_Name"));
                    session.setAttribute("email", rs.getString("email"));
                    session.setAttribute("phone", rs.getString("phoneNumber"));
                    redirectPage = "Homepage.jsp";
                } else {
                    req.setAttribute("error", "Invalid customer credentials");
                    req.getRequestDispatcher("/WEB-INF/view/customer/SignUp.jsp").forward(req, res);
                    return;
                }

            } else {
                // HARD-CODED CREDENTIALS FOR DEMO USERS
                boolean valid = false;
                HttpSession session = req.getSession();

                if ("admin".equals(role) && email.equals("ronelladias17@gmail.com") && password.equals("123")) {
                    session.setAttribute("role", "admin");
                    session.setAttribute("name", "Ronella");
                    valid = true;
                    redirectPage = "/WEB-INF/view/Admin/admin.jsp";
                } else if ("operator".equals(role) && email.equals("sandy@gmail.com") && password.equals("123")) {
                    session.setAttribute("role", "Kalum");
                    session.setAttribute("name", "Bus Operator");
                    valid = true;
                    redirectPage = "BopDashboardServlet";
                } else if ("financer".equals(role) && email.equals("visakya@gmail.com") && password.equals("123")) {
                    session.setAttribute("role", "financer");
                    session.setAttribute("name", "Samayanthie");
                    valid = true;
                    redirectPage = "getPaymentTableServlet";
                }

                if (!valid) {
                    req.setAttribute("error", "Invalid " + role + " credentials");
                    req.getRequestDispatcher("/WEB-INF/view/customer/SignUp.jsp").forward(req, res);
                    return;
                }
            }

            req.getRequestDispatcher(redirectPage).forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Something went wrong.");
            req.getRequestDispatcher("/WEB-INF/view/customer/SignUp.jsp").forward(req, res);
        }
    }
}
