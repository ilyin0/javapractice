package by.bsu.ilyin.entities;

import java.util.Objects;

public class IdEntity<K> {

    protected K id;

    public IdEntity(K id) {
        this.id = id;
    }

    public IdEntity() {
    }

    public K getId() {
        return id;
    }

    public IdEntity setId(K id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdEntity)) return false;
        IdEntity<?> idEntity = (IdEntity<?>) o;
        return getId().equals(idEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
