package by.bsu.ilyin.model;

import by.bsu.ilyin.entities.recipe.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class RecipeController extends AbstractController<Recipe, Integer> {

    public RecipeController() {
        this.mapper=new ObjectMapper();
        this.database = new Database("C:\\Users\\ilyin\\Study\\EPAM\\Valeriya\\Labs\\Yourecipe\\javapractice\\YouRecipe\\recipe.json");
        this.converter=new RecipeConverter();
    }

    @Override
    public Recipe[] getAll() throws IOException {
        return this.getAllAsList().toArray(new Recipe[0]);
    }

    @Override
    public Recipe update(Recipe entity) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(Recipe entity) {
        return false;
    }
}
