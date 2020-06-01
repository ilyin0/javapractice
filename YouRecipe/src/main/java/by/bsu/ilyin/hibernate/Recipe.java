package by.bsu.ilyin.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "\"Recipe\"")
public class Recipe {
    private long rid;
    private String name;

    public Recipe() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//сложно
    @Column(name = "RID", nullable = false, precision = 0)
    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (rid != recipe.rid) return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (rid ^ (rid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
