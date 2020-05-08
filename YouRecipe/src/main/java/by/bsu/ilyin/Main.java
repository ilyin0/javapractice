package by.bsu.ilyin;

import by.bsu.ilyin.UI.ConsoleUI;
import by.bsu.ilyin.UI.UI;
import by.bsu.ilyin.dao.Converter;
import by.bsu.ilyin.dao.UserConverter;
import by.bsu.ilyin.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

public class Main {
    public static void main(String[] args) throws Exception {
        UI ui = new ConsoleUI();
        ui.start();
    }
}
