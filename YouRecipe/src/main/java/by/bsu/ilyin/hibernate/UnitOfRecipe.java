package by.bsu.ilyin.hibernate;

import javax.persistence.*;

@Entity
public class UnitOfRecipe {
    private long uorid;
    private long amount;
    private String measure;
    private Recipe recipeByRecipeId;
    private Product productByProductId;

    @Id
    @Column(name = "UORID", nullable = false, precision = 0)
    public long getUorid() {
        return uorid;
    }

    public void setUorid(long uorid) {
        this.uorid = uorid;
    }

    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "measure", nullable = false, length = 255)
    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnitOfRecipe that = (UnitOfRecipe) o;

        if (uorid != that.uorid) return false;
        if (amount != that.amount) return false;
        if (measure != null ? !measure.equals(that.measure) : that.measure != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (uorid ^ (uorid >>> 32));
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + (measure != null ? measure.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "recipeId", referencedColumnName = "RID", nullable = false)
    public Recipe getRecipeByRecipeId() {
        return recipeByRecipeId;
    }

    public void setRecipeByRecipeId(Recipe recipeByRecipeId) {
        this.recipeByRecipeId = recipeByRecipeId;
    }

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "PID", nullable = false)
    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }
}
