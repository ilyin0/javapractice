package by.bsu.ilyin;

import by.bsu.ilyin.UI.ConsoleUI;
import by.bsu.ilyin.UI.UI;

public class Main {
    public static void main(String[] args) throws Exception {
        UI ui = new ConsoleUI();
        //ui.start();
        System.out.print(ui.constructEntity("by.bsu.ilyin.entities.recipe.Recipe"));
    }
}
