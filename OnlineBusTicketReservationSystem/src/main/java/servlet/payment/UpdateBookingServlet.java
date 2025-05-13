package servlet.payment;
import service.payment.IBookingService;
import service.payment.IPaymentService;
import service.payment.BookingServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 * Servlet implementation class UpdateBookingServlet
 */
@WebServlet("/UpdateBookingServlet")
public class UpdateBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Cache control headers to prevent browser caching
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // Proxies
		
		response.setContentType("text/html");
		
		//Get the updated booking id and booking status from the request
	    int bookingID = Integer.parseInt(request.getParameter("bookingID"));
	    String status = request.getParameter("status");
	    
	    //Create a service type object to acquire services
		IBookingService bookingService = new BookingServiceImpl();
		bookingService.updateBooking(bookingID, status);
		
		//After updation redirecting back to the index.jsp
		response.sendRedirect(request.getContextPath() + "/WEB-INF/view/payment/PaymentTable.jsp");
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		//dispatcher.forward(request, response);
	}

}
