package servlet.payment;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import model.payment.Payment;
import service.payment.IPaymentService;
import service.payment.PaymentServiceImpl;

/**
 * Servlet implementation class archivesDeleteServlet
 */
@WebServlet("/archivesDeleteServlet")
public class archivesDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public archivesDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Purpose of this servlet is to delete the archived payment and thus delete these payments from the entire system.
		
		response.setContentType("text/html");
		
		//Retrieve payment id from the request
	    int paymentID = Integer.parseInt(request.getParameter("paymentID"));
	    
	    //Create a service type object to acquire services
	    IPaymentService paymentService = new PaymentServiceImpl();
	    
	    //Delete the payment
	    paymentService.DeletePayment(paymentID);
	    
	    //Get the updated archived list
	    ArrayList<Payment> archivedPayments = paymentService.getArchivedPayments(1);
	    
	    //Forward to ArchivedPayments.jsp
	    request.setAttribute("archivedPayments", archivedPayments);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/payment/ArchivedPayments.jsp");
	    dispatcher.forward(request, response);
	    
	   
		
		 
		
	}

}
