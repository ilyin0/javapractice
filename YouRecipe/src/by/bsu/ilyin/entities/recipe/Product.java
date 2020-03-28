package by.bsu.ilyin.entities.recipe;

import java.util.StringJoiner;

public class Product {

    private String name;
    private String type = "";

    public Product(){super();}

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, String type) {
        this.name = name;
        this.type = type;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("type='" + type + "'")
                .toString();
    }
}
