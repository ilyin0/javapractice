package by.bsu.ilyin.entities.recipe;

public class RecipeContent {
    private Step[]steps;

    public RecipeContent(Step[] steps) {
        this.steps = steps;
    }

    public Step[] getSteps() {
        return steps;
    }

    public void setSteps(Step[] steps) {
        this.steps = steps;
    }
}
