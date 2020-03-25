package by.bsu.ilyin.entities.recipe;

import java.util.Arrays;
import java.util.List;

public class Recipe {
    private int id;
    private String name;
    private List<UnitOfRecipe>unitsOfRecipe;
    private RecipeContent recipeContent;

    public Recipe(){
        super();
    }

    public Recipe(int id, String name, UnitOfRecipe[] unitsOfRecipe, RecipeContent recipeContent) {
        this.id = id;
        this.name = name;
        this.unitsOfRecipe = Arrays.asList(unitsOfRecipe);
        this.recipeContent = recipeContent;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UnitOfRecipe> getUnitsOfRecipe() {
        return unitsOfRecipe;
    }

    public Recipe setUnitsOfRecipe(UnitOfRecipe[] unitsOfRecipe) {
        this.unitsOfRecipe = Arrays.asList(unitsOfRecipe);
        return this;
    }

    public RecipeContent getRecipeContent() {
        return recipeContent;
    }

    public void setRecipeContent(RecipeContent recipeContent) {
        this.recipeContent = recipeContent;
    }

}
