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
import model.payment.Status;


/**
 * Servlet implementation class getPayServlet
 */
@WebServlet("/getPayServlet")
public class getPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getPayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get the status type from the request
		String type= request.getParameter("type");
		ArrayList<Payment> statusList = new ArrayList<>();
		
		//If the type = "pending"
		if("pending".equals(type)) {
			Status sType = new Pending(); //Create Pending type object
			statusList = sType.getPaymentsByStatus(type); //Calls getPaymentsByStatus method 
			
			//Retrieve the relevant payments
			request.setAttribute("statusList", statusList);
			
			//Forward the list to StatusResults.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/payment/StatusResults.jsp");
			dispatcher.forward(request, response);
		}
		
		//If the type = "completed"
		else if("completed".equals(type)){
			Status sType = new Completed(); //Create Completed type object
			statusList = sType.getPaymentsByStatus(type); //Calls getPaymentsByStatus method 
			
			request.setAttribute("statusList", statusList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/payment/StatusResults.jsp");
			dispatcher.forward(request, response);
		}
		
		//If the type = "failed"
		else if("failed".equals(type)){
			Status sType = new Failed(); //Create Failed type object
			statusList = sType.getPaymentsByStatus(type); //Calls getPaymentsByStatus method 
			
			request.setAttribute("statusList", statusList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/payment/StatusResults.jsp");
			dispatcher.forward(request, response);
		}
		
		//If the type = "refunded"
		else if("refunded".equals(type)){
			Status sType = new Refunded(); //Create Refunded type object
			statusList = sType.getPaymentsByStatus(type); //Calls getPaymentsByStatus method 
			
			request.setAttribute("statusList", statusList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/payment/StatusResults.jsp");
			dispatcher.forward(request, response);
		}
		
		//If the type = "all"
		else if("all".equals(type)) {
			response.sendRedirect(request.getContextPath() + "/getPaymentTableServlet"); //Redirect to show all payments
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
