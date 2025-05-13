package service.admin;

import model.admin.AdminRoutes;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteService {

    public List<AdminRoutes> getAllRoutes() {
        List<AdminRoutes> routes = new ArrayList<>();
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            String sqlQuery = "SELECT * from BusRoute";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AdminRoutes route = new AdminRoutes();
                route.setRouteId(rs.getInt("busRouteId"));
                route.setRouteName(rs.getString("busRouteName"));
                routes.add(route);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routes;
    }

    public AdminRoutes getRouteById(int id) {
        AdminRoutes route = null;
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            String query = "SELECT busRouteId, busRouteName FROM BusRoute WHERE busRouteId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                route = new AdminRoutes();
                route.setRouteId(rs.getInt("busRouteId"));
                route.setRouteName(rs.getString("busRouteName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return route;
    }

    public int getNextRouteId() {
        int nextRouteId = 1;
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            String getMaxIdQuery = "SELECT MAX(busRouteId) AS maxId FROM BusRoute";
            PreparedStatement stmt = conn.prepareStatement(getMaxIdQuery);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nextRouteId = rs.getInt("maxId") + 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextRouteId;
    }

    public boolean addRoute(String routeName) {
        boolean success = false;
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            String sqlQuery = "INSERT INTO BusRoute (busRouteName) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setString(1, routeName);
            success = stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean updateRoute(int id, String routeName) {
        boolean success = false;
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            String updateQuery = "UPDATE BusRoute SET busRouteName = ? WHERE busRouteId = ?";
            PreparedStatement stmt = conn.prepareStatement(updateQuery);
            stmt.setString(1, routeName);
            stmt.setInt(2, id);
            success = stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean deleteRoute(int id) {
        boolean success = false;
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            String deleteQuery = "DELETE FROM BusRoute WHERE busRouteId = ?";
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);
            stmt.setInt(1, id);
            success = stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}