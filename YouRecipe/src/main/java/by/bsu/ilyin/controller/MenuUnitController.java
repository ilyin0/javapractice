package by.bsu.ilyin.controller;

import by.bsu.ilyin.entities.MenuUnit;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.List;

public class MenuUnitController extends Controller<MenuUnit,Integer> {
    ObjectMapper mapper;
    Database database;
    Converter converter;

    public MenuUnitController() throws SQLException, ClassNotFoundException {
        super();
        this.mapper=new ObjectMapper();
        this.database = new Database("C:\\Study\\EPAM\\javapractice\\YouRecipe\\db\\UI\\UI.json");
        this.converter=new MenuUnitConverter();
    }

    @Override
    public MenuUnit[] getAll() {
        return getAllAsList().toArray(new MenuUnit[0]);
    }

    @SneakyThrows
    @Override
    public List<MenuUnit> getAllAsList(){
        return this.converter.fromJSONFileToList(this.database.getDb());
    }

    @Override
    public MenuUnit getById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public MenuUnit getByName(String name){
        try {
            MenuUnit[] menuUnits = this.getAll();
            for (MenuUnit menuUnit : menuUnits) {
                if (name.equals(menuUnit.getName())) return menuUnit;
            }
            throw new Exception("MenuUnit having this name doesn't exist");
        }
        catch(Exception exception) {
            logger.error(exception.getMessage());
        }
        return null;
    }

    @Override
    public boolean create(MenuUnit entity) {
        return false;
    }

    @Override
    public boolean update(MenuUnit entity) {
        return false;
    }
}
