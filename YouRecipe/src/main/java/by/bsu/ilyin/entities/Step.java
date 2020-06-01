package by.bsu.ilyin.entities;

import javax.persistence.*;

@Entity
@Table(name = "\"Step\"")
public class Step {
    private long sid;
    private String image;
    private String describe;
    private long stepNumber;
    private Recipe recipeByRecipeId;

    @Id
    @Column(name = "SID", nullable = false, precision = 0)
    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "image", nullable = false, length = 255)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "describe", nullable = false, length = 255)
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Basic
    @Column(name = "stepNumber", nullable = false, precision = 0)
    public long getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(long stepNumber) {
        this.stepNumber = stepNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Step step = (Step) o;

        if (sid != step.sid) return false;
        if (stepNumber != step.stepNumber) return false;
        if (image != null ? !image.equals(step.image) : step.image != null) return false;
        if (describe != null ? !describe.equals(step.describe) : step.describe != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (sid ^ (sid >>> 32));
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (describe != null ? describe.hashCode() : 0);
        result = 31 * result + (int) (stepNumber ^ (stepNumber >>> 32));
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
}
