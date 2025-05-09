package servlet.bus;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bus.Bus;
import service.bus.BusServiceImpl;
import service.bus.IBusService;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Servlet to list all buses.
 */
@WebServlet("/ListBusServlet")
public class ListBusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles the GET request to display the list of buses.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Fetch the list of buses from the service
		IBusService busService = new BusServiceImpl();
		List<Bus> busList = busService.getBuses();

		// If the bus list is null or empty, initialize it to avoid null pointer
		if (busList == null) {
		    busList = new ArrayList<>();
		}


		// Set the bus list as a request attribute for the JSP
		request.setAttribute("busList", busList);

		// Forward the request to the ListBuses.jsp page to display the buses
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListBuses.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Handles POST requests by delegating to doGet (same behavior for listing
	 * buses).
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response); // Delegate to doGet for the same behavior
	}
}
