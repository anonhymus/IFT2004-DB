package org.anonhyme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/3/2016.
 */
public class OracleJDBC {

    public void SetUp() {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found");
            e.printStackTrace();
            return;
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE", "anonhyme",
                    "Uber4jar");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connection, consulter la console");
            e.printStackTrace();
            return;

        }

        if(connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

    }
}
