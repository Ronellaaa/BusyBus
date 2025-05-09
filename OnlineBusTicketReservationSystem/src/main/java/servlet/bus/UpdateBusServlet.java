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
import java.time.LocalTime;

/**
 * Servlet to handle updating an existing bus.
 */
@WebServlet("/UpdateBusServlet")
public class UpdateBusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles the POST request to update bus information.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            // Retrieve bus ID from the form
            int busId = Integer.parseInt(request.getParameter("bus_id"));
            
            System.out.println("Updating bus ID: " + busId);


            // Create a Bus object and populate it with the form data
            Bus bus = new Bus();
            bus.setBusId(busId);
            bus.setBusName(request.getParameter("bus_name"));
            bus.setBusType(request.getParameter("bus_type"));
            bus.setPricePerSeat(Double.parseDouble(request.getParameter("price_per_seat")));
            bus.setTotalSeats(Integer.parseInt(request.getParameter("total_seats")));
            bus.setDepartureTime(LocalTime.parse(request.getParameter("departure_time")));
            bus.setArrivalTime(LocalTime.parse(request.getParameter("arrival_time")));
            bus.setSeatsAvailable(Integer.parseInt(request.getParameter("seats_available")));

            // Set active status from form
            String isActiveParam = request.getParameter("is_active");
            bus.setActive("true".equals(isActiveParam));

            // Call the service to update the bus in the database
            IBusService busService = new BusServiceImpl();
            busService.updateBus(busId, bus);

            response.sendRedirect("BopDashboardServlet?message=addSuccess");

        } catch (Exception e) {
            // Handle any exceptions (e.g., invalid input, database errors)
            handleError(request, response, "Failed to update bus: " + e.getMessage());
        }
    }

    /**
     * Helper method to handle error forwarding.
     */
    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage)
            throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/bus/GetBus.jsp");
        dispatcher.forward(request, response);
    }
}


