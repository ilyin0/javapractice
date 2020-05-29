package by.bsu.ilyin.service;

import by.bsu.ilyin.dao.MenuUnitDAO;
import by.bsu.ilyin.entities.MenuUnit;

import java.sql.SQLException;

public class MenuUnitService extends Service<MenuUnit,Integer> {

    public MenuUnitService() throws SQLException, ClassNotFoundException {
        DAO =new MenuUnitDAO();
    }

    public MenuUnit getByName(String name){
        return DAO.getByName(name);
    }
}
