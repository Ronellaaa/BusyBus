package util;

import java.sql.*;

public class DBConnection {
	
	private static DBConnection instance;
	
	private Connection conn;
	
	private DBConnection () {
		try {
			String url = "jdbc:mysql://localhost:3306/busyBus";
			String userName = "root";
			String password = "Oggy2012";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url,userName,password);	
			
			System.out.println("SuccessfuL!! DB connection Established!");
			
		}catch(Exception e) {
			System.err.println("Error connecting the database: "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static DBConnection getInstance() {
		if(instance == null) {
			synchronized(DBConnection.class) {
				if(instance==null) {
					instance = new DBConnection ();
				}
			}
		}
		return instance;
	}
	
	public Connection getConnection() {
		return conn;
	}

}
