package service.admin;
import model.admin.AdminRoutes;
import java.util.List;

public interface RouteServiceInterface {
	List<AdminRoutes> getAllRoutes();
    AdminRoutes getRouteById(int id);
    int getNextRouteId();
    boolean addRoute(String routeName);
    boolean updateRoute(int id, String routeName);
    boolean deleteRoute(int id);

}
