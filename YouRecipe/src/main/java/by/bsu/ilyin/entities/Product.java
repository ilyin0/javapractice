package by.bsu.ilyin.entities;

import javax.persistence.*;

@Entity
@Table(name = "\"Product\"")
public class Product {
    private long pid;
    private String name;
    private String type;
    private long calorificValue;
    private long proteins;
    private long fats;
    private long carbohydrates;

    @Id
    @Column(name = "PID", nullable = false, precision = 0)
    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "calorificValue", nullable = false, precision = 0)
    public long getCalorificValue() {
        return calorificValue;
    }

    public void setCalorificValue(long calorificValue) {
        this.calorificValue = calorificValue;
    }

    @Basic
    @Column(name = "proteins", nullable = false, precision = 0)
    public long getProteins() {
        return proteins;
    }

    public void setProteins(long proteins) {
        this.proteins = proteins;
    }

    @Basic
    @Column(name = "fats", nullable = false, precision = 0)
    public long getFats() {
        return fats;
    }

    public void setFats(long fats) {
        this.fats = fats;
    }

    @Basic
    @Column(name = "carbohydrates", nullable = false, precision = 0)
    public long getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(long carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (pid != product.pid) return false;
        if (calorificValue != product.calorificValue) return false;
        if (proteins != product.proteins) return false;
        if (fats != product.fats) return false;
        if (carbohydrates != product.carbohydrates) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (type != null ? !type.equals(product.type) : product.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (pid ^ (pid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (int) (calorificValue ^ (calorificValue >>> 32));
        result = 31 * result + (int) (proteins ^ (proteins >>> 32));
        result = 31 * result + (int) (fats ^ (fats >>> 32));
        result = 31 * result + (int) (carbohydrates ^ (carbohydrates >>> 32));
        return result;
    }
}
