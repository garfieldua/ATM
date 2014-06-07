package com.vabank.atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	private static DBConnector instance = new DBConnector();
	public static final String URL = "jdbc:mysql://37.140.192.173/u0012798_default";
	public static final String USER = "u0012798_default";
	public static final String PASSWORD = "74S**7q2";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	// private constructor
	private DBConnector() {
		try {
			// Step 2: Load MySQL Java driver
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection createConnection() {

		Connection connection = null;
		try {
			// Step 3: Establish Java MySQL connection
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("ERROR: Unable to Connect to Database.");
		}
		return connection;
	}

	public static Connection getConnection() {
		return instance.createConnection();
	}
}
