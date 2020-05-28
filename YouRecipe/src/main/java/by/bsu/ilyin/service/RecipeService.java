package by.bsu.ilyin.service;

import by.bsu.ilyin.controller.RecipeController;
import by.bsu.ilyin.entities.Recipe;

import java.sql.SQLException;

public class RecipeService extends Service<Recipe,Integer> {

    public RecipeService() throws SQLException, ClassNotFoundException {
        controller=new RecipeController();
    }

    public Recipe getByName(String name){
        return controller.getByName(name);
    }
}
