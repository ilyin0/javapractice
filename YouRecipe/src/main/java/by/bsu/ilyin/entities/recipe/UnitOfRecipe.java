package by.bsu.ilyin.entities.recipe;

import by.bsu.ilyin.entities.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.StringJoiner;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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

    @Override
    public String toString() {
        return new StringJoiner(", ", UnitOfRecipe.class.getSimpleName() + "[", "]")
                .add("product=" + product)
                .add("amount=" + amount)
                .add("measure='" + measure + "'")
                .toString();
    }
}
