package by.bsu.ilyin;

import by.bsu.ilyin.UI.ConsoleUI;
import by.bsu.ilyin.UI.UI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static void main(String[] args) throws Exception {
        UI ui = new ConsoleUI();
        ui.start();

    }
}
