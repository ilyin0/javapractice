package by.bsu.ilyin.entities.recipe;

import java.util.StringJoiner;

public class UnitOfRecipe {
    private Product product;
    private Integer amount;
    private String measure;

    public UnitOfRecipe(){super();}

    public UnitOfRecipe(Product product, int amount, String measure) {
        this.product = product;
        this.amount = amount;
        this.measure = measure;
    }

    public Product getProduct() {
        return product;
    }

    public UnitOfRecipe setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public UnitOfRecipe setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getMeasure() {
        return measure;
    }

    public UnitOfRecipe setMeasure(String measure) {
        this.measure = measure;
        return this;
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
