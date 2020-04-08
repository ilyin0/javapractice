package by.bsu.ilyin.model;

import by.bsu.ilyin.entities.MenuUnit;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class MenuUnitController extends Controller<MenuUnit,Integer> {
    public MenuUnitController() {
        this.mapper=new ObjectMapper();
        this.database = new Database("C:\\Users\\ilyin\\Study\\EPAM\\Valeriya\\Labs\\Yourecipe\\javapractice\\YouRecipe\\db\\UI\\UI.json");
        this.converter=new MenuUnitConverter();
    }

    @Override
    public MenuUnit[] getAll() throws IOException {
        return getAllAsList().toArray(new MenuUnit[0]);
    }

    @Override
    public boolean updateDb(List<MenuUnit> list) {
        return updateDb(list.toArray(new MenuUnit[0]));
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

}
