package by.bsu.ilyin.entities;

import java.util.StringJoiner;

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

}
