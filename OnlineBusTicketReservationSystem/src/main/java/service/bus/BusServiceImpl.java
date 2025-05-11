package service.bus;

import model.bus.Bus;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Implementation of Bus Service for handling bus-related operations.
 */
public class BusServiceImpl implements IBusService {

    /** Initialize logger */
    public static final Logger log = Logger.getLogger(BusServiceImpl.class.getName());

    

    /**
     * Add a new bus to the database.
     */
    @Override
    public void addBus(Bus bus) {
        String query = """
            INSERT INTO busDetails 
            (bus_name, bus_type, price_per_seat, total_seats, departure_time, arrival_time, seats_available, is_active) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);
            """;

        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, bus.getBusName());
                ps.setString(2, bus.getBusType());
                ps.setDouble(3, bus.getPricePerSeat());
                ps.setInt(4, bus.getTotalSeats());
                ps.setTime(5, Time.valueOf(bus.getDepartureTime()));
                ps.setTime(6, Time.valueOf(bus.getArrivalTime()));
                ps.setInt(7, bus.getSeatsAvailable());
                ps.setBoolean(8, bus.isActive());

                ps.executeUpdate();
                connection.commit();
            }

        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to add bus", e);
            if (connection != null) {
                try {
                    connection.rollback();  // Rollback the original connection
                } catch (SQLException rollbackEx) {
                    log.log(Level.SEVERE, "Rollback failed", rollbackEx);
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();  //  Ensure the connection is closed
                } catch (SQLException e) {
                    log.log(Level.SEVERE, "Failed to close connection", e);
                }
            }
        }
    }

    /**
     * Retrieve a bus by ID.
     */
    @Override
    public Bus getBusByID(int busId) {
        String query = "SELECT * FROM busDetails WHERE bus_id = ?";
        Bus bus = null;

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, busId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    bus = new Bus();
                    bus.setBusId(rs.getInt("bus_id"));
                    bus.setBusName(rs.getString("bus_name"));
                    bus.setBusType(rs.getString("bus_type"));
                    bus.setPricePerSeat(rs.getDouble("price_per_seat"));
                    bus.setTotalSeats(rs.getInt("total_seats"));
                    bus.setDepartureTime(rs.getTime("departure_time").toLocalTime());
                    bus.setArrivalTime(rs.getTime("arrival_time").toLocalTime());
                    bus.setSeatsAvailable(rs.getInt("seats_available"));
                    bus.setActive(rs.getBoolean("is_active"));
                }
            }

        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to fetch bus by ID", e);
        }

        return bus;
    }

    /**
     * Retrieve all buses.
     */
    @Override
    public ArrayList<Bus> getBuses() {
        String query = "SELECT * FROM busDetails";
        ArrayList<Bus> buses = new ArrayList<>();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bus bus = new Bus();
                bus.setBusId(rs.getInt("bus_id"));
                bus.setBusName(rs.getString("bus_name"));
                bus.setBusType(rs.getString("bus_type"));
                bus.setPricePerSeat(rs.getDouble("price_per_seat"));
                bus.setTotalSeats(rs.getInt("total_seats"));
                bus.setDepartureTime(rs.getTime("departure_time").toLocalTime());
                bus.setArrivalTime(rs.getTime("arrival_time").toLocalTime());
                bus.setSeatsAvailable(rs.getInt("seats_available"));
                bus.setActive(rs.getBoolean("is_active"));

                buses.add(bus);
            }

        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to retrieve buses", e);
        }

        return buses;
    }

    /**
     * Update a bus.
     */
    @Override
    public Bus updateBus(int busId, Bus bus) {
        String query = """
            UPDATE busDetails SET 
                bus_name = ?, bus_type = ?, price_per_seat = ?, total_seats = ?, 
                departure_time = ?, arrival_time = ?, seats_available = ?, is_active = ? 
            WHERE bus_id = ?;
            """;

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, bus.getBusName());
            ps.setString(2, bus.getBusType());
            ps.setDouble(3, bus.getPricePerSeat());
            ps.setInt(4, bus.getTotalSeats());
            ps.setTime(5, Time.valueOf(bus.getDepartureTime()));
            ps.setTime(6, Time.valueOf(bus.getArrivalTime()));
            ps.setInt(7, bus.getSeatsAvailable());
            ps.setBoolean(8, bus.isActive());
            ps.setInt(9, busId);

            ps.executeUpdate();

        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to update bus", e);
        }

        return getBusByID(busId);
    }

    /**
     * Remove a bus.
     */
    @Override
    public void removeBus(int busId) {
        String query = "DELETE FROM busDetails WHERE bus_id = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, busId);
            ps.executeUpdate();

        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to remove bus", e);
        }
    }

    /**
     * Get the number of active buses.
     */
    @Override
    public int getActiveBusCount() {
        String query = "SELECT COUNT(*) FROM busDetails WHERE is_active = TRUE";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to count active buses", e);
        }

        return 0;
    }

    /**
     * Get the total number of buses.
     */
    @Override
    public int getTotalBusCount() {
        String query = "SELECT COUNT(*) FROM busDetails";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to count total buses", e);
        }

        return 0;
    }

    /**
     * Count of buses departing after the current time.
     */
    @Override
    public int getUpcomingDeparturesTodayCount() {
        String query = "SELECT COUNT(*) FROM busDetails WHERE departure_time > CURTIME()";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to count upcoming departures", e);
        }

        return 0;
    }

    /**
     * Get all buses along with their route names (if present).
     */
    @Override
    public ArrayList<Bus> getBusesWithRouteNames() {
        String query = """
            SELECT b.bus_id, b.bus_name, b.bus_type, b.price_per_seat, b.total_seats,
                   b.departure_time, b.arrival_time, b.seats_available, b.is_active,
                   r.busRouteName
            FROM busDetails b
            LEFT JOIN BusRoute r ON b.bus_id = r.busRouteId;
            """;

        ArrayList<Bus> busList = new ArrayList<>();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bus bus = new Bus();
                bus.setBusId(rs.getInt("bus_id"));
                bus.setBusName(rs.getString("bus_name"));
                bus.setBusType(rs.getString("bus_type"));
                bus.setPricePerSeat(rs.getDouble("price_per_seat"));
                bus.setTotalSeats(rs.getInt("total_seats"));
                bus.setDepartureTime(rs.getTime("departure_time").toLocalTime());
                bus.setArrivalTime(rs.getTime("arrival_time").toLocalTime());
                bus.setSeatsAvailable(rs.getInt("seats_available"));
                bus.setActive(rs.getBoolean("is_active"));

                String routeName = rs.getString("busRouteName");
                bus.setRouteName(routeName != null ? routeName : "No Route");

                busList.add(bus);
            }

        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to fetch buses with route names", e);
        }

        return busList;
    }
    
    /**
     * Combined method to search buses based on name, type, and active status.
     * It dynamically filters buses based on the provided parameters.
     */
    @Override
    public ArrayList<Bus> searchBuses(String name, String type, String isActiveStr) {
        // Start with all buses
        List<Bus> buses = getBuses();

        // Filter buses based on provided parameters
        if (name != null && !name.isBlank()) {
            buses = buses.stream()
                    .filter(bus -> bus.getBusName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (type != null && !type.isBlank()) {
            buses = buses.stream()
                    .filter(bus -> bus.getBusType().equalsIgnoreCase(type))
                    .collect(Collectors.toList());
        }

        if (isActiveStr != null && !isActiveStr.isBlank()) {
            boolean isActive = Boolean.parseBoolean(isActiveStr);
            buses = buses.stream()
                    .filter(bus -> bus.isActive() == isActive)
                    .collect(Collectors.toList());
        }

        return new ArrayList<>(buses);  // Convert to ArrayList if needed
    }

}