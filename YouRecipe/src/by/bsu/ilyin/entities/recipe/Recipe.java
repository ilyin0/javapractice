package by.bsu.ilyin.entities.recipe;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Recipe {
    private int id;
    private String name;
    private List<UnitOfRecipe>unitsOfRecipe;
    private List<Step>steps;

    public Recipe(){
        super();
    }

    public Recipe(int id, String name, UnitOfRecipe[] unitsOfRecipe, Step[]steps) {
        this.id = id;
        this.name = name;
        this.unitsOfRecipe = Arrays.asList(unitsOfRecipe);
        this.steps = Arrays.asList(steps);
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

    public Recipe setUnitsOfRecipe(List<UnitOfRecipe> unitsOfRecipe) {
        this.unitsOfRecipe = unitsOfRecipe;
        return this;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public Recipe setSteps(List<Step> steps) {
        this.steps = steps;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Recipe.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("unitsOfRecipe=" + unitsOfRecipe)
                .add("steps=" + steps)
                .toString();
    }
}
