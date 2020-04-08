package by.bsu.ilyin.entities;

import java.util.Objects;

public class IdEntity {

    private Integer id;

    public IdEntity(Integer id) {
        this.id = id;
    }

    public IdEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public IdEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdEntity)) return false;
        IdEntity idEntity = (IdEntity) o;
        return getId().equals(idEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
