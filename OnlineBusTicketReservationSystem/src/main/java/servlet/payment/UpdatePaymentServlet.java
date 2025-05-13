package servlet.payment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import service.payment.IBookingService;
import service.payment.IPaymentService;
import service.payment.BookingServiceImpl;
import model.payment.Booking;
import service.payment.PaymentServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdatePaymentServlet
 */
@WebServlet("/UpdatePaymentServlet")
public class UpdatePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePaymentServlet() {
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



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        response.setContentType("text/html");
		        
		     // Cache control headers to prevent browser caching
		        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		        response.setDateHeader("Expires", 0); // Proxies

		        try {
		        	//Get the parameters from the request
		            int paymentID = Integer.parseInt(request.getParameter("paymentID"));
		            String status = request.getParameter("status");
		            int bookingID = Integer.parseInt(request.getParameter("bookingID"));
		            
		          //Create a service type object to acquire services
		            IPaymentService paymentService = new PaymentServiceImpl();
		            paymentService.updatePayment(paymentID, status); //Update the payment
		            
		          //Create a service type object to acquire services
		            IBookingService bookingService = new BookingServiceImpl();
		            ArrayList<Booking> bookList = bookingService.getBookingsById(bookingID); //Get the booking details 
		            
		            //Create a session variable
		            HttpSession session = request.getSession();
		            
		            //Make sure the Booking object list is not null
		            if (!bookList.isEmpty()) {
		            	
		                // Create a notification
		                Map<String, String> notification = new HashMap<>();
		                notification.put("id", UUID.randomUUID().toString()); //Create a unique id and store in  the Map
		                notification.put("type", "warning");
		                notification.put("message", "Payment updated! Booking status may need updating.");
		                //notification.put("url", "/WEB-INF/view/payment/viewBooking.jsp");
		                notification.put("url", request.getContextPath() + "/viewBookingServlet");
		                //Store notification in session variable
		                session.setAttribute("notification", notification);
		                
		                // Also store the booking list 
		                session.setAttribute("bookingListForPayment", bookList);
		                
		            } 
		            
		            else {
		                session.setAttribute("notificationMsg", "Payment updated successfully! (No related bookings found)");
		            }
		            
		            // Redirect to the referring page
		            String referer = request.getHeader("referer");
		            response.sendRedirect(referer != null ? referer : request.getContextPath() + "/WEB-INF/view/payment/PaymentTable.jsp");
		           
		            //When creating the Map if the errors are encountered
		        } catch (NumberFormatException e) {
		            HttpSession session = request.getSession();
		            session.setAttribute("notificationMsg", "Invalid payment ID format");
		            response.sendRedirect(request.getContextPath() + "/WEB-INF/view/payment/PaymentTable.jsp");
		            
		        } catch (Exception e) {
		            HttpSession session = request.getSession();
		            session.setAttribute("notificationMsg", "Error updating payment: " + e.getMessage());
		            response.sendRedirect(request.getContextPath() + "/WEB-INF/view/payment/PaymentTable.jsp");
		        }
		    }
		}


