package by.bsu.ilyin.service;

import by.bsu.ilyin.entities.recipe.Recipe;
import by.bsu.ilyin.model.RecipeController;

public class RecipeService extends Service<Recipe,Integer> {

    public RecipeService(){
        controller=new RecipeController();
    }

    public Recipe getByName(String name){
        return controller.getByName(name);
    }
}
