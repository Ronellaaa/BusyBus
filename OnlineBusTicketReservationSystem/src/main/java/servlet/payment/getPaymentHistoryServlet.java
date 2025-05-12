package servlet.payment;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import model.payment.*;
import service.payment.*;
 
@WebServlet("/getPaymentHistoryServlet")
public class getPaymentHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getPaymentHistoryServlet() {
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
		
		//Get the payment from the AddPaymentServlet
		Payment payment = (Payment)request.getAttribute("payment");
		
		// Get the payment id of the payment object
		int payId = payment.getPaymentId();
		
	    //Call payment service to process payment
		IPaymentService paymentService = new PaymentServiceImpl();
		
		//Get the all past payments
		ArrayList<Payment> payList = paymentService.getPaymentsById(payId);
		
		//Forward the list of payments to PaymentHistory.jsp
		request.setAttribute("payList", payList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/payment/PaymentHistory.jsp");
		dispatcher.forward(request, response);
	}

}
