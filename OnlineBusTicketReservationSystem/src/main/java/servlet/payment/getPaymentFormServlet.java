package servlet.payment;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.payment.Payment;

import java.io.IOException;

/**
 * Servlet implementation class getPaymentFormServlet
 */
@WebServlet("/getPaymentFormServlet")
public class getPaymentFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getPaymentFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//int bookingID = Integer.parseInt(request.getParameter("booking_id"));
		//double amount = Double.parseDouble(request.getParameter("totalPrice"));
		
		//Create Payment object
	    Payment payment = new Payment();
	    
	    // Get form data and set to payment object
	    payment.setBookingId(Integer.parseInt(request.getParameter("booking_id")));
	    payment.setAmount(Double.parseDouble(request.getParameter("totalPrice")));
	    
	    request.setAttribute("payment", payment);
	    
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/payment/PaymentForm.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
	}

}
