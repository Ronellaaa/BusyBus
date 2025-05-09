package service.bus;


import model.bus.Bus;
import util.DBConnection;
import java.sql.Time;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of Bus Service for handling bus-related operations.
 * @version 1.0
 */
public class BusServiceImpl implements IBusService {

    /** Initialize logger */
    public static final Logger log = Logger.getLogger(BusServiceImpl.class.getName());

    private static Connection connection;
    private static PreparedStatement preparedStatement;

    // Initialize database connection and table (if required)
    static {
        // Only create in dev
        if (System.getProperty("env", "dev").equals("dev")) {
            createBusTable();
        }
    }

    /**
     * Create the Bus table in the database (or drop and recreate it).
     */
    public static void createBusTable() {
        String dropTableQuery = "DROP TABLE IF EXISTS busDetails;";
        String createTableQuery = """
            CREATE TABLE busDetails (
                bus_id INT PRIMARY KEY AUTO_INCREMENT,
                bus_name VARCHAR(50) NOT NULL,
                bus_type VARCHAR(50),
                price_per_seat DECIMAL(10,2),
                total_seats INT NOT NULL,
                departure_time TIME,
                arrival_time TIME,
                seats_available INT,
                is_active BOOLEAN DEFAULT TRUE
            );
            """;

        try {
            connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropTableQuery);
            statement.executeUpdate(createTableQuery);
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }


    /**
     * Add a new bus to the database.
     * @param bus the Bus object to add
     */
    @Override
    public void addBus(Bus bus) {
        String insertQuery = """
            INSERT INTO busDetails 
            (bus_name, bus_type, price_per_seat, total_seats, departure_time, arrival_time, seats_available, is_active) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);
            """;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false); // Disable auto-commit for transaction management

            preparedStatement = connection.prepareStatement(insertQuery);

            // Set parameters for the prepared statement
            preparedStatement.setString(1, bus.getBusName());
            preparedStatement.setString(2, bus.getBusType());
            preparedStatement.setDouble(3, bus.getPricePerSeat());
            preparedStatement.setInt(4, bus.getTotalSeats());
            preparedStatement.setTime(5, Time.valueOf(bus.getDepartureTime()));
            preparedStatement.setTime(6, Time.valueOf(bus.getArrivalTime()));
            preparedStatement.setInt(7, bus.getSeatsAvailable());
            preparedStatement.setBoolean(8, bus.isActive());

            // Execute the query
            preparedStatement.executeUpdate();
            connection.commit();  // Commit transaction

        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
            try {
                if (connection != null) connection.rollback(); // Rollback in case of error
            } catch (SQLException ex) {
                log.log(Level.SEVERE, "Rollback failed: " + ex.getMessage());
            }
        } finally {
            closeResources();
        }
    }


    /**
     * Retrieves a single bus by ID.
     * @param busId the ID of the bus to retrieve
     * @return the Bus object, or null if not found
     */
    @Override
    public Bus getBusByID(int busId) {
        // -- get_bus_by_id
        String selectByIdQuery = "SELECT * FROM busDetails WHERE bus_id = ?;";
        Bus bus = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(selectByIdQuery);
            preparedStatement.setInt(1, busId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                bus = new Bus();
                bus.setBusId(resultSet.getInt("bus_id"));
                bus.setBusName(resultSet.getString("bus_name"));
                bus.setBusType(resultSet.getString("bus_type"));
                bus.setPricePerSeat(resultSet.getDouble("price_per_seat"));
                bus.setTotalSeats(resultSet.getInt("total_seats"));
                bus.setDepartureTime(resultSet.getTime("departure_time").toLocalTime());
                bus.setArrivalTime(resultSet.getTime("arrival_time").toLocalTime());
                bus.setSeatsAvailable(resultSet.getInt("seats_available"));
                bus.setActive(resultSet.getBoolean("is_active"));
            }

        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        } finally {
            closeResources();
        }

        return bus;
    }

    /**
     * Retrieves a list of all buses.
     * @return a list of all buses
     */
    @Override
    public ArrayList<Bus> getBuses() {
        // -- get_all_buses
        String selectAllQuery = "SELECT * FROM busDetails;";
        ArrayList<Bus> buses = new ArrayList<>();

        try {
            connection = DBConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(selectAllQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Bus bus = new Bus();
                bus.setBusId(resultSet.getInt("bus_id"));
                bus.setBusName(resultSet.getString("bus_name"));
                bus.setBusType(resultSet.getString("bus_type"));
                bus.setPricePerSeat(resultSet.getDouble("price_per_seat"));
                bus.setTotalSeats(resultSet.getInt("total_seats"));
                bus.setDepartureTime(resultSet.getTime("departure_time").toLocalTime());
                bus.setArrivalTime(resultSet.getTime("arrival_time").toLocalTime());
                bus.setSeatsAvailable(resultSet.getInt("seats_available"));
                bus.setActive(resultSet.getBoolean("is_active"));

                buses.add(bus);
            }

        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        } finally {
            closeResources();
        }

        return buses;
    }


    /**
     * Update an existing bus in the database.
     * @param busId the ID of the bus to update
     * @param bus the Bus object containing updated information
     * @return the updated Bus object
     */
    @Override
    public Bus updateBus(int busId, Bus bus) {
        // -- update_bus
        String updateQuery = """
            UPDATE busDetails 
            SET bus_name = ?, bus_type = ?, price_per_seat = ?, total_seats = ?, 
                departure_time = ?, arrival_time = ?, seats_available = ?, is_active = ? 
            WHERE bus_id = ?;
            """;

        try {
            connection = DBConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(updateQuery);

            // Set parameters
            preparedStatement.setString(1, bus.getBusName());
            preparedStatement.setString(2, bus.getBusType());
            preparedStatement.setDouble(3, bus.getPricePerSeat());
            preparedStatement.setInt(4, bus.getTotalSeats());
            preparedStatement.setTime(5, Time.valueOf(bus.getDepartureTime()));
            preparedStatement.setTime(6, Time.valueOf(bus.getArrivalTime()));
            preparedStatement.setInt(7, bus.getSeatsAvailable());
            preparedStatement.setBoolean(8, bus.isActive());
            preparedStatement.setInt(9, busId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        } finally {
            closeResources();
        }

        return getBusByID(busId);  // Return updated record
    }

    /**
     * Remove a bus from the database.
     * @param busId the ID of the bus to remove
     */
    @Override
    public void removeBus(int busId) {
        // -- delete_bus
        String deleteQuery = "DELETE FROM busDetails WHERE bus_id = ?;";

        try {
            connection = DBConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, busId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        } finally {
            closeResources();
        }
    }

    
    /**
     * Get the count of active buses.
     * @return the number of active buses
     */
    public int getActiveBusCount() {
        String query = "SELECT COUNT(*) FROM busDetails WHERE is_active = TRUE";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);  // Return the count of active buses
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return 0;  // Return 0 if no active buses found or error occurred
    }

    /**
     * Get the total count of buses.
     * @return the total number of buses
     */
    public int getTotalBusCount() {
        String query = "SELECT COUNT(*) FROM busDetails";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);  // Return the total count of buses
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return 0;  // Return 0 if no buses found or error occurred
    }

    /**
     * Get the count of upcoming departures (buses departing after the current time today).
     * @return the number of buses departing today after the current time
     */
    @Override
    public int getUpcomingDeparturesTodayCount() {
        // -- get_upcoming_departures
        String query = "SELECT COUNT(*) FROM busDetails WHERE departure_time > CURTIME();";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);  // Return the count of buses with departure time in the future
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return 0;  // Return 0 if no upcoming departures found or error occurred
    }

    
    /**
     * Get all buses with their route names
     * @return List of Bus objects with route names
     */
    @Override
    public ArrayList<Bus> getBusesWithRouteNames() {
        // -- get_all_buses_with_route_names
        String query = """
            SELECT b.bus_id, b.bus_name, b.bus_type, b.price_per_seat, b.total_seats, 
                   b.departure_time, b.arrival_time, b.seats_available, b.is_active, 
                   r.busRouteName 
            FROM busDetails b 
            LEFT JOIN BusRoute r ON b.bus_id = r.busRouteId;
            """;

        ArrayList<Bus> busList = new ArrayList<>();
        try {
            connection = DBConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bus bus = new Bus();
                bus.setBusId(resultSet.getInt("bus_id"));
                bus.setBusName(resultSet.getString("bus_name"));
                bus.setBusType(resultSet.getString("bus_type"));
                bus.setPricePerSeat(resultSet.getDouble("price_per_seat"));
                bus.setTotalSeats(resultSet.getInt("total_seats"));
                bus.setDepartureTime(resultSet.getTime("departure_time").toLocalTime());
                bus.setArrivalTime(resultSet.getTime("arrival_time").toLocalTime());
                bus.setSeatsAvailable(resultSet.getInt("seats_available"));
                bus.setActive(resultSet.getBoolean("is_active"));
                bus.setRouteName(resultSet.getString("busRouteName")); // Route name from the join
                
             // Handle potential null for route name, default to "No Route" if not available
                String routeName = resultSet.getString("busRouteName");
                bus.setRouteName(routeName != null ? routeName : "No Route");

                busList.add(bus);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error while fetching buses with route names", e);
        } finally {
            closeResources();
        }

        return busList;
    }

    /**
     * Close resources (PreparedStatement, Connection) safely after database operation.
     */
    private void closeResources() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close(); // Returns it to pool if using connection pooling
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }
}
