package by.bsu.ilyin.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBC {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = null;
        String userName = "c##yourecipe";
        String password = "adminYR";
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", userName, password);
        return connection;
    }
}

