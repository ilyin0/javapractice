package by.bsu.ilyin.dao;

import by.bsu.ilyin.entities.MenuUnit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MenuUnitController extends Controller<MenuUnit,Integer> {
    public MenuUnitController() {
        this.mapper=new ObjectMapper();
        this.database = new Database("C:\\Study\\EPAM\\javapractice\\YouRecipe\\db\\UI\\UI.json");
        this.converter=new MenuUnitConverter();
    }

    @Override
    public MenuUnit[] getAll() throws IOException, SQLException, JSONException, ClassNotFoundException {
        return getAllAsList().toArray(new MenuUnit[0]);
    }

    @Override
    public boolean updateDb(List<MenuUnit> list) {
        return updateDb(list.toArray(new MenuUnit[0]));
    }

    @java.lang.Override
    public List<MenuUnit> getAllAsList() throws SQLException, ClassNotFoundException, JSONException, IOException {
        return this.converter.fromJSONFileToList(this.database.getDb());
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
