package by.bsu.ilyin.entities.recipe;

import by.bsu.ilyin.entities.IdEntity;

import java.util.StringJoiner;

public class Product extends IdEntity {

    private String name;
    private String type = "";
    private int calorificValue;

    public Product(){super();}

    public Product(String name, int calorificValue) {
        this.name = name;
        this.calorificValue = calorificValue;
    }

    public Product(String name, String type, int calorificValue) {
        this.name = name;
        this.type = type;
        this.calorificValue = calorificValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCalorificValue() {
        return calorificValue;
    }

    public void setCalorificValue(int calorificValue) {
        this.calorificValue = calorificValue;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("type='" + type + "'")
                .toString();
    }
}
