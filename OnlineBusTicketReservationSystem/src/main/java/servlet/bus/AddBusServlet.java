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
 * Servlet to handle adding a new bus to the database.
 */
@WebServlet("/AddBusServlet")
public class AddBusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    

    /**
     * Handles the POST request to add a bus.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            // Create Bus object and set its properties from the form
            model.bus.Bus bus = new Bus();
            bus.setBusName(request.getParameter("bus_name"));
            bus.setBusType(request.getParameter("bus_type"));
            bus.setPricePerSeat(Double.parseDouble(request.getParameter("price_per_seat")));
            bus.setTotalSeats(Integer.parseInt(request.getParameter("total_seats")));
            bus.setDepartureTime(LocalTime.parse(request.getParameter("departure_time")));
            bus.setArrivalTime(LocalTime.parse(request.getParameter("arrival_time")));
            bus.setSeatsAvailable(Integer.parseInt(request.getParameter("seats_available")));
            bus.setActive("true".equals(request.getParameter("is_active")));

            // Call the service to add the bus to the database
            IBusService busService = new BusServiceImpl();
            busService.addBus(bus);

            // Set the newly added bus
            request.setAttribute("bus", bus);
            
            System.out.println("Adding new bus: " + bus.getBusName()); //to check
            
            

            // Also set updated bus list for display
            request.setAttribute("busList", busService.getBuses());

            // Forward to ListBuses.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/bus/ListBuses.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            handleError(request, response, "Failed to add bus: " + e.getMessage());
        }
    }


    /**
     * Helper method to handle error forwarding.
     */
    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage)
            throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/webapp/BusForm.jsp");
        dispatcher.forward(request, response);
    }
}
