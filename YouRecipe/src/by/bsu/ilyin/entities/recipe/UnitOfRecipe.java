package by.bsu.ilyin.entities.recipe;

public class UnitOfRecipe {
    private Product product;
    private int amount;
    private String measure;

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

    public int getAmount() {
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
}
