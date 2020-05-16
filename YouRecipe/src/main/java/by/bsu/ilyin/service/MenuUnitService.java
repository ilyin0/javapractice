package by.bsu.ilyin.service;

import by.bsu.ilyin.dao.MenuUnitController;
import by.bsu.ilyin.entities.MenuUnit;

import java.sql.SQLException;

public class MenuUnitService extends Service<MenuUnit,Integer> {

    public MenuUnitService() throws SQLException, ClassNotFoundException {
        controller=new MenuUnitController();
    }

    public MenuUnit getByName(String name){
        return controller.getByName(name);
    }
}
