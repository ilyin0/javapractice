package by.bsu.ilyin.dbc;

import lombok.Data;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

@Data
public class DBC {
    String userName;
    String password;
    String URL;
    OracleDataSource ds;
    Connection connection;

    public DBC() throws SQLException, ClassNotFoundException {
        userName = "c##yourecipe";
        password = "adminYR";
        URL = "jdbc:oracle:thin:@localhost:1521:orcl";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        ds = new OracleDataSource();
        ds.setURL(URL);
        connection = ds.getConnection(userName, password);
    }

}

