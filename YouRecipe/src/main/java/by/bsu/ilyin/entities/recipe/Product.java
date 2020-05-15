package by.bsu.ilyin.entities.recipe;

import by.bsu.ilyin.entities.IdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.StringJoiner;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Product extends IdEntity {
    private String name;
    private String type = " ";
    private int calorificValue;
    private int proteins;
    private int fats;
    private int carbohydrates;

    public Product(){super();}

    public Product(String name, int calorificValue, int proteins, int fats, int carbohydrates) {
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
}
