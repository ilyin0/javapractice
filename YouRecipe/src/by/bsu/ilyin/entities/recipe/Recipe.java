package by.bsu.ilyin.entities.recipe;

import by.bsu.ilyin.entities.IdEntity;

import java.util.List;
import java.util.StringJoiner;

public class Recipe extends IdEntity {
    private String name;
    private List<UnitOfRecipe>unitsOfRecipe;
    private List<Step>steps;

    public Recipe(){
        super();
    }

    public Recipe(int id, String name, List<UnitOfRecipe> unitsOfRecipe, List<Step> steps) {
        super(id);
        this.name = name;
        this.unitsOfRecipe = unitsOfRecipe;
        this.steps = steps;
    }


    public String getName() {
        return name;
    }

    public Recipe setName(String name) {
        this.name = name;
        return this;
    }

    public List<UnitOfRecipe> getUnitsOfRecipe() {
        return unitsOfRecipe;
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
                .add("id=" + getId())
                .add("name='" + name + "'")
                .add("unitsOfRecipe=" + unitsOfRecipe)
                .add("steps=" + steps)
                .toString();
    }

}
