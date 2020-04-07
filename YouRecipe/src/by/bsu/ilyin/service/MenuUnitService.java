package by.bsu.ilyin.service;

import by.bsu.ilyin.entities.MenuUnit;
import by.bsu.ilyin.model.MenuUnitController;

public class MenuUnitService extends Service<MenuUnit,Integer> {

    public MenuUnitService(){
        controller=new MenuUnitController();
    }

    public MenuUnit getByName(String name){
        return controller.getByName(name);
    }
}
