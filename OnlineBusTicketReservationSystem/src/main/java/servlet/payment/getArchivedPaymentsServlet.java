package servlet.payment;
import model.payment.*;
import service.payment.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class getArchivedPaymentsServlet
 */
@WebServlet("/getArchivedPaymentsServlet")
public class getArchivedPaymentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getArchivedPaymentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Create a service type object to acquire services
		IPaymentService paymentService = new PaymentServiceImpl();
		
		//Get the archived payments
		ArrayList<Payment> archivedPayments = paymentService.getArchivedPayments(1); 
		
		//Forward to ArchivedPayments.jsp
		request.setAttribute("archivedPayments", archivedPayments);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/payment/ArchivedPayments.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
