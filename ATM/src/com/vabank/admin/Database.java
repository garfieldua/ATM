package com.vabank.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private Connection con;
	private static Database instance = null;
	
	public static final String URL = "jdbc:mysql://37.140.192.173/u0012798_default";
	public static final String USER = "u0012798_default";
	public static final String PASSWORD = "74S**7q2";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	// private constructor
	private Database() {
		try {
			// Step 2: Load MySQL Java driver
			Class.forName(DRIVER_CLASS);
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public ResultSet execute(String query) {

		try {
			Statement s = con.createStatement();
			
			s.execute(query);
			ResultSet rs = s.getResultSet();

			return rs;
			
		}

		catch (SQLException e) {
			System.out.println("Error: " + e);
		}
		return null;
	}
}
