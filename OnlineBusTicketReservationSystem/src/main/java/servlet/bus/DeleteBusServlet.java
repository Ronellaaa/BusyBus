package servlet.bus;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.bus.BusServiceImpl;
import service.bus.IBusService;

/**
 * Servlet to delete a bus by ID.
 */
@WebServlet("/DeleteBusServlet")
public class DeleteBusServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DeleteBusServlet() {
		super();
	}

	/**
	 * Only POST is used for deletion for safety (not GET).
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int busId = Integer.parseInt(request.getParameter("bus_id"));

		IBusService busService = new BusServiceImpl();
		busService.removeBus(busId);

        // Set success message
        request.setAttribute("deleteSuccess", true);  // Indicate successful deletion

     // ✅ 🔁 REDIRECT INSTEAD OF FORWARDING TO DELETED RESOURCE
     		// Sends a redirect to ListBuses.jsp or dashboard with a query parameter
     		response.sendRedirect("ListBusServlet?message=deleteSuccess");

	}
}
