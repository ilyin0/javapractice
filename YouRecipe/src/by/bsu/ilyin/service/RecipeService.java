package by.bsu.ilyin.service;

import by.bsu.ilyin.entities.recipe.Recipe;
import by.bsu.ilyin.model.RecipeController;

import java.io.IOException;
import java.util.List;

public class RecipeService extends Service<Recipe,Integer> {

    public RecipeService(){
        controller=new RecipeController();
    }

    @Override
    public Recipe[] getAll() {
        try {
            return getAllAsList().toArray(new Recipe[0]);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return new Recipe[0];
    }

    @Override
    public boolean updateDb(List<Recipe> list) {
        return updateDb(list.toArray(new Recipe[0]));
    }
}
