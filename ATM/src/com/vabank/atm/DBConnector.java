package com.vabank.atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	private static DBConnector instance = new DBConnector();
	public static final String URL = "jdbc:mysql://mysql5.000webhost.com/a8561715_ATM";
	public static final String USER = "a8561715_root";
	public static final String PASSWORD = "ATMpr0ject";
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
