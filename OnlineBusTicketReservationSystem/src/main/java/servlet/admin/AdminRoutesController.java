package servlet.admin;
import service.admin.RouteService;


import model.admin.AdminRoutes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/admin-add-routes", "/admin-view-routes", "/admin-edit-routes", "/admin-delete-routes"})

public class AdminRoutesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final RouteService routeService = new RouteService();
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();

        if ("/admin-add-routes".equals(path)) {
            int nextRouteId = routeService.getNextRouteId();
            request.setAttribute("nextRouteId", nextRouteId);
            request.getRequestDispatcher("/WEB-INF/view/Admin/admin-add-routes.jsp").forward(request, response);

        } else if ("/admin-edit-routes".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            AdminRoutes route = routeService.getRouteById(id);
            request.setAttribute("route", route);
            request.getRequestDispatcher("/WEB-INF/view/Admin/admin-edit-routes.jsp").forward(request, response);

        } else if ("/admin-delete-routes".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            routeService.deleteRoute(id);
            response.sendRedirect(request.getContextPath() + "/admin-view-routes");

        } else {
            List<AdminRoutes> routes = routeService.getAllRoutes();
            request.setAttribute("routeList", routes);
            request.getRequestDispatcher("/WEB-INF/view/Admin/admin-view-routes.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/admin-add-routes".equals(path)) {
            String routeName = request.getParameter("route-name");
            routeService.addRoute(routeName);
            response.sendRedirect(request.getContextPath() + "/admin-view-routes");

        } else if ("/admin-edit-routes".equals(path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String routeName = request.getParameter("route-name");
            routeService.updateRoute(id, routeName);
            response.sendRedirect(request.getContextPath() + "/admin-view-routes");
        }
    }
}