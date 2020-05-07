package by.bsu.ilyin.entities.recipe;

import by.bsu.ilyin.entities.IdEntity;

import java.util.StringJoiner;

public class UnitOfRecipe extends IdEntity {
    private Product product;
    private Integer amount;
    private String measure;
    private int recipeId;

    public UnitOfRecipe(){super();}

    public UnitOfRecipe(Product product, int amount, String measure, int recipeId) {
        this.product = product;
        this.amount = amount;
        this.measure = measure;
        this.recipeId = recipeId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UnitOfRecipe.class.getSimpleName() + "[", "]")
                .add("product=" + product)
                .add("amount=" + amount)
                .add("measure='" + measure + "'")
                .toString();
    }
}
