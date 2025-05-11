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

/**
 * Servlet to retrieve and display a bus by its ID.
 */
@WebServlet("/GetBusServlet")
public class GetBusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles the POST request to fetch a bus by ID and forward to the edit page.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        // Retrieve bus ID from the form parameter
        String busIDParam = request.getParameter("bus_id");

        if (busIDParam == null || busIDParam.isEmpty()) {
            // Handle missing bus ID by showing an error message
            request.setAttribute("errorMessage", "Bus ID is missing!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/bus/ListBuses.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            int busID = Integer.parseInt(busIDParam);

            // Fetch the bus details from the service layer
            IBusService busService = new BusServiceImpl();
            Bus bus = busService.getBusByID(busID);

            if (bus != null) {
            	
                // If bus found, forward to GetBus.jsp for editing
                request.setAttribute("bus", bus);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/bus/GetBus.jsp");
                dispatcher.forward(request, response);
                
                request.setAttribute("updateSuccess", true);
            } else {
                // If bus not found, show an error message
                request.setAttribute("errorMessage", "Bus not found for ID: " + busID);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/bus/ListBuses.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NumberFormatException e) {
            // Handle invalid bus ID format
            request.setAttribute("errorMessage", "Invalid Bus ID format.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/bus/ListBuses.jsp");
            dispatcher.forward(request, response);
        }
    }
}
