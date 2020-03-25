package by.bsu.ilyin.model;

import by.bsu.ilyin.entities.recipe.Recipe;

public class RecipeController extends AbstractController<Recipe, Integer> {

    Database database = new Database("C:\\Users\\ilyin\\Study\\java\\Practice\\file_write_and_read_demo\\user.json");

    @Override
    public Recipe[] getAll() {
        return new Recipe[0];
    }

    @Override
    public Recipe getEntityById(Integer id) {
        return null;
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
