package by.bsu.ilyin.service;

import by.bsu.ilyin.dao.MenuUnitController;
import by.bsu.ilyin.entities.MenuUnit;

public class MenuUnitService extends Service<MenuUnit,Integer> {

    public MenuUnitService(){
        controller=new MenuUnitController();
    }

    public MenuUnit getByName(String name){
        return controller.getByName(name);
    }
}
