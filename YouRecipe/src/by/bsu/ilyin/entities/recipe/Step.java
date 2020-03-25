package by.bsu.ilyin.entities.recipe;

public class Step {
    private String image;
    private String describe;

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
}
