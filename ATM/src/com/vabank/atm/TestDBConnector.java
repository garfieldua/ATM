package com.vabank.atm;

import static org.junit.Assert.*;
import java.sql.ResultSet;
import org.junit.Test;
import java.sql.Connection;
import com.mysql.jdbc.Statement;
import java.sql.SQLException;

public class TestDBConnector {

	@Test
	public void testDBDataReceive() {
		ResultSet rs = null;
        Connection connection = null;
        java.sql.Statement statement = null; 
        String query = "SELECT * FROM card_accounts";
        
        try {           
            connection = DBConnector.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
             
            if (rs.next()) {
                System.out.println(rs.getString("id_account"));
                System.out.println(rs.getString("pin_code"));
                System.out.println(rs.getInt("amount"));
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
