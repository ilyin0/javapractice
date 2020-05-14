package by.bsu.ilyin.dao;

import by.bsu.ilyin.entities.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RecipeController extends Controller<Recipe, Integer> {

    public RecipeController() {
        this.mapper=new ObjectMapper();
        this.database = new Database("/Users/ilyin/Documents/Study/university/epam/javapractice/YouRecipe/db/recipe.json");
        this.converter=new RecipeConverter();
    }

    @Override
    public Recipe[] getAll() throws IOException, SQLException, JSONException, ClassNotFoundException {
        return this.getAllAsList().toArray(new Recipe[0]);
    }

    @Override
    public boolean updateDb(List<Recipe> list) {
        return updateDb(list.toArray(new Recipe[0]));
    }

    @Override
    public List<Recipe> getAllAsList() throws SQLException, ClassNotFoundException, JSONException, JsonProcessingException {
        return null;
    }

    @Override
    public Recipe getByName(String name){
        try {
            Recipe[] recipes = this.getAll();
            for (Recipe recipe : recipes) {
                if (name.equals(recipe.getName())) return recipe;
            }
            throw new Exception("Recipe having this name doesn't exist");
        }
        catch(Exception exception) {
            logger.error(exception.getMessage());
        }
        return null;
    }

}
