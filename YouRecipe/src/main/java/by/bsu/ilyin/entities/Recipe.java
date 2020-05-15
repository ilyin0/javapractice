package by.bsu.ilyin.entities;

import by.bsu.ilyin.entities.recipe.Step;
import by.bsu.ilyin.entities.recipe.UnitOfRecipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.StringJoiner;

@Data
@EqualsAndHashCode(callSuper = true)
public class Recipe extends IdEntity {
    private String name;
    int userId;
    private List<UnitOfRecipe>unitsOfRecipe;
    private List<Step>steps;

    public Recipe(){
        super();
    }

    public Recipe(int id, String name, int userId, List<UnitOfRecipe> unitsOfRecipe, List<Step> steps) {
        super(id);
        this.name = name;
        this.userId = userId;
        this.unitsOfRecipe = unitsOfRecipe;
        this.steps = steps;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Recipe.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("name='" + name + "'")
                .add("userId="+userId)
                .add("unitsOfRecipe=" + unitsOfRecipe)
                .add("steps=" + steps)
                .toString();
    }

}
