package by.bsu.ilyin.model;

import java.io.IOException;

public abstract class AbstractController<E,K> {
    public abstract E[] getAll() throws IOException;
    public abstract E getEntityById(K id) throws Exception;
    public abstract E update(E entity) throws IOException;
    public abstract boolean delete(K id) throws IOException;
    public abstract boolean create(E entity) throws IOException;
}
