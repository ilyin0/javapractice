package by.bsu.ilyin.entities.recipe;

import by.bsu.ilyin.entities.IdEntity;

import java.util.StringJoiner;

public class Step  extends IdEntity {
    private String image;
    private String describe;
    private int stepNumber;
    private int recipeId;


    public Step(){super();}

    public Step(String image, String describe) {
        this.image = image;
        this.describe = describe;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public Step(Integer id, String image, String describe, int stepNumber, int recipeId) {
        super(id);
        this.image = image;
        this.describe = describe;
        this.stepNumber = stepNumber;
        this.recipeId = recipeId;
    }

    public Step(String image, String describe, int stepNumber, int recipeId) {
        this.image = image;
        this.describe = describe;
        this.stepNumber = stepNumber;
        this.recipeId = recipeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


    @Override
    public String toString() {
        return "Step{" +
                "image='" + image + '\'' +
                ", describe='" + describe + '\'' +
                ", stepNumber=" + stepNumber +
                ", recipeId=" + recipeId +
                '}';
    }


}
