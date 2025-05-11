
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private Connection conn;

    private static final String URL = "jdbc:mysql://localhost:3306/BusyBus";
    private static final String USER = "root";
    private static final String PASSWORD = "OOP_WEBSITE1";

    // Private constructor to prevent direct instantiation
    private DBConnection() {
        connect();
    }

    // Method to establish the DB connection
    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ DB connection established successfully!");
        } catch (Exception e) {
            System.err.println("❌ Error connecting to the database: " + e.getMessage());
        }
    }

    // Public method to get the Singleton instance
    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    // Public method to get the DB connection
    public Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                connect(); // Reconnect if closed
            }
        } catch (SQLException e) {
            System.err.println("⚠️ Could not verify connection status: " + e.getMessage());
        }
        return conn;
    }
}