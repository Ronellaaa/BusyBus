package servlet.payment;

import service.payment.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import model.payment.Payment;

/**
 * Servlet implementation class AddPaymentServlet
 */
@WebServlet("/AddPaymentServlet")
public class AddPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPaymentServlet() {
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
		// TODO Auto-generated method stub

	    // Set response type 
	    response.setContentType("text/html");

	    //Create Payment object
	    Payment payment = new Payment();
	    
	    // Get form data and set to payment object
	    payment.setBookingId(Integer.parseInt(request.getParameter("bookingId")));
	    payment.setCardNumber(request.getParameter("cardNumber"));
	    payment.setCardHolderName(request.getParameter("cardHolderName"));
	    payment.setExpiryDate(request.getParameter("expiryDate"));
	    payment.setCvv(request.getParameter("cvv"));
	    payment.setAmount(Double.parseDouble(request.getParameter("amount")));
	    payment.setPaymentStatus("Pending"); // Default status

	    //  Call payment service to process payment
	    IPaymentService paymentService = new PaymentServiceImpl();
	    
	    //Add the payment to the database
	    paymentService.addpayment(payment);
	    
	    // to share with getPaymentHistoryServlet
	    request.setAttribute("payment", payment);
	    
	    // Forward to getPaymentHistoryServlet
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/getPaymentHistoryServlet");
		dispatcher.forward(request, response);
	}

}
