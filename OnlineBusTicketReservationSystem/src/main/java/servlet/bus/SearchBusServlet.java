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

@WebServlet("/SearchBusServlet")
public class SearchBusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("busName");
        String type = request.getParameter("busType");
        String isActiveStr = request.getParameter("isActive");

        IBusService busService = new BusServiceImpl();

        // Call the service to fetch the filtered buses based on available search fields
        List<Bus> filteredBuses = busService.searchBuses(name, type, isActiveStr);

        // Set the filtered list as an attribute for the JSP
        request.setAttribute("busList", filteredBuses);
        request.setAttribute("showActions", true);

        // Forward the request to the ListBuses JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/bus/ListBuses.jsp");
        dispatcher.forward(request, response);
    }
}
