package util;

import java.sql.*;

public class DBConnection {
	
	private static DBConnection instance;
	
	private Connection conn;
	
	private DBConnection () {
		try {
			String url = "jdbc:mysql://127.0.0.1:3306/BusyBus";
			String userName = "root";
			String password = "OOP_WEBSITE1";
			
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
