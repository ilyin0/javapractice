package by.bsu.ilyin.entities.recipe;

import by.bsu.ilyin.entities.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.StringJoiner;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class Product extends IdEntity {
    private String name;
    private String type = " ";
    private int calorificValue;
    private int proteins;
    private int fats;
    private int carbohydrates;

    public Product(){super();}

    public Product(int id, String name, int calorificValue, int proteins, int fats, int carbohydrates) {
        super(id);
        this.name = name;
        this.calorificValue = calorificValue;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public Product(String name, String type, int calorificValue) {
        this.name = name;
        this.type = type;
        this.calorificValue = calorificValue;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("id='" + getId() + "'")
                .add("name='" + name + "'")
                .add("type='" + type + "'")
                .add("calorificValue=" + calorificValue)
                .add("proteins=" + proteins)
                .add("fats=" + fats)
                .add("carbohydrates=" + carbohydrates)
                .toString();
    }
}
