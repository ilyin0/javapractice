package by.bsu.ilyin.UI;

import java.sql.SQLException;

public class ConsoleUI extends UI {
    public ConsoleUI() throws SQLException, ClassNotFoundException {
        super();
        outStream = System.out;
        inStream = System.in;
    }

}
