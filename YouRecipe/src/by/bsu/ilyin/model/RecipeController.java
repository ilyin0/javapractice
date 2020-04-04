package by.bsu.ilyin.model;

import by.bsu.ilyin.entities.recipe.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class RecipeController extends Controller<Recipe, Integer> {

    public RecipeController() {
        this.mapper=new ObjectMapper();
        this.database = new Database("C:\\Users\\ilyin\\Study\\EPAM\\Valeriya\\Labs\\Yourecipe\\javapractice\\YouRecipe\\db\\recipe.json");
        this.converter=new RecipeConverter();
    }

    @Override
    public Recipe[] getAll() throws IOException {
        return this.getAllAsList().toArray(new Recipe[0]);
    }

    @Override
    public boolean updateDb(List<Recipe> list) {
        return updateDb(list.toArray(new Recipe[0]));
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
