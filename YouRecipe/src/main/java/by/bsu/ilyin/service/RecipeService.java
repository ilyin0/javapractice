package by.bsu.ilyin.service;

import by.bsu.ilyin.dao.RecipeDAO;
import by.bsu.ilyin.entities.Recipe;

import java.sql.SQLException;

public class RecipeService extends Service<Recipe,Long> {

    public RecipeService() throws SQLException, ClassNotFoundException {
        DAO =new RecipeDAO();
    }

    public Recipe getByName(String name){
        return DAO.getByName(name);
    }
}
