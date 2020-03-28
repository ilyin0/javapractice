package by.bsu.ilyin.entities.recipe;

import java.util.StringJoiner;

public class Step {
    private String image;
    private String describe;

    public Step(){super();}

    public Step(String image, String describe) {
        this.image = image;
        this.describe = describe;
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
        return new StringJoiner(", ", Step.class.getSimpleName() + "[", "]")
                .add("image='" + image + "'")
                .add("describe='" + describe + "'")
                .toString();
    }
}
