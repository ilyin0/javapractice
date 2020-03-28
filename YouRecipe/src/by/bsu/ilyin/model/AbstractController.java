package by.bsu.ilyin.model;

import by.bsu.ilyin.entities.IdEntity;
import by.bsu.ilyin.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public abstract class AbstractController<E extends IdEntity,K> {

    ObjectMapper mapper;
    Database database;
    Converter converter;

    public abstract E[] getAll() throws IOException;
    public abstract E update(E entity) throws IOException;
    public abstract boolean delete(K id) throws IOException;
    public abstract boolean create(E entity) throws IOException;

    public List<E> getAllAsList() throws IOException {
        return this.converter.fromJSONFileToList(this.database.getDb());
    }

    public E getEntityById(K id) throws Exception{
        try {
            E[] es = this.getAll();
            for (E e : es) {
                if (id.equals(e.getId())) return e;
            }
            throw new Exception("This entity doesn't exist!");
        }
        catch(Exception exception) {
            System.out.print(exception.toString());
            return null;
        }
    }
}
