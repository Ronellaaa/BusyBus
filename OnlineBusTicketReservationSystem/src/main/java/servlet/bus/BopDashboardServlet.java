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
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class BopDashboardServlet
 * This servlet handles the dashboard page for the Bus Operator,
 * displaying the bus list and other relevant information.
 */
@WebServlet("/BopDashboardServlet")
public class BopDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Instantiate bus service
        IBusService busService = new BusServiceImpl();

        // Fetch the counts of active buses, total buses, and upcoming departures
        int activeBuses = busService.getActiveBusCount();
        int totalBuses = busService.getTotalBusCount();
        int upcomingDeparturesToday = busService.getUpcomingDeparturesTodayCount();

        // Fetch the list of all buses for display
        List<Bus> busList = busService.getBuses();
        if (busList == null || busList.isEmpty()) {
            busList = new ArrayList<>();
        }

        // Set attributes for use in the JSP
        request.setAttribute("busList", busList);
        request.setAttribute("activeBuses", activeBuses);
        request.setAttribute("totalBuses", totalBuses);
        request.setAttribute("upcomingDeparturesToday", upcomingDeparturesToday);

        // Forward the request to the BopDashboard.jsp to display the bus list on the dashboard
        RequestDispatcher dispatcher = request.getRequestDispatcher("/BopDashboard.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);  // Reuse doGet for POST requests
    }
}