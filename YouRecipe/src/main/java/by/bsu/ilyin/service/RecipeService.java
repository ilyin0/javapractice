package by.bsu.ilyin.service;

import by.bsu.ilyin.dao.RecipeController;
import by.bsu.ilyin.entities.Recipe;

public class RecipeService extends Service<Recipe,Integer> {

    public RecipeService(){
        controller=new RecipeController();
    }

    public Recipe getByName(String name){
        return controller.getByName(name);
    }
}
