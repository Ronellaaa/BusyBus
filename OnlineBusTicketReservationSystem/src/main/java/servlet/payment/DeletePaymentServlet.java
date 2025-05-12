package servlet.payment;
import service.payment.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class DeletePaymentServlet
 */
@WebServlet("/DeletePaymentServlet")
public class DeletePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePaymentServlet() {
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
		//Pupose of this servlet is to delete the payments of the payment table and put into archives list
		
		response.setContentType("text/html");
		
		//Get the payment id from the request
	    int paymentID = Integer.parseInt(request.getParameter("paymentID"));
	    
	    //Create a service type object to acquire services
	    IPaymentService paymentService = new PaymentServiceImpl();
	    paymentService.removePayment(paymentID);
	    
	    //Get the referring page from the request header
	    String referer = request.getHeader("referer");
	    
	    // If referer is not null, redirect to it, otherwise redirect to index.jsp
	    if (referer != null && !referer.isEmpty()) {
	        response.sendRedirect(referer);
	    } else {
	        response.sendRedirect(request.getContextPath() + "/index.jsp");
	    }
	}

}
