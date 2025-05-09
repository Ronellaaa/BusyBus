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
 * Servlet implementation class BusCardsServlet
 * This servlet handles the card-style bus listing for the Bus Operator,
 * showing each bus in an individual card with edit/delete actions.
 */
@WebServlet("/BusCardsServlet")
public class BusCardsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Instantiate bus service
        IBusService busService = new BusServiceImpl();

        // Fetch all buses with route names
        List<Bus> busList = busService.getBusesWithRouteNames();
        if (busList == null) {
            busList = new ArrayList<>();
        }


        // Set bus list for the JSP page
        request.setAttribute("busList", busList);

        // Forward to JSP for rendering
        RequestDispatcher dispatcher = request.getRequestDispatcher("/BusCards.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);  // Reuse doGet for POST requests
    }
}
