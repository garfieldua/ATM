package com.vabank.admin;

import java.sql.ResultSet;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConnector {

	@Test
	public void testDBDataReceive() {
		ResultSet rs = null;
        Connection connection = null;
        java.sql.Statement statement = null; 
        String query = "SELECT * FROM natural_person";
        
        try {           
            connection = DBConnector.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
             
            while (rs.next()) {
                System.out.println(rs.getString("card_number"));
                System.out.println(rs.getString("pin"));
                System.out.println("==================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
	}

}
