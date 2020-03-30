package by.bsu.ilyin.model;

import by.bsu.ilyin.entities.IdEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public abstract class AbstractController<E extends IdEntity,K> {

    ObjectMapper mapper;
    Database database;
    Converter converter;

    public abstract E[] getAll() throws IOException;

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

    public int findIndex(E e) throws IOException {
        int index = -1;
        for(int i=0;i<this.getAll().length;++i){
            if(this.getAll()[i].getId()==e.getId()) { index = i; }
        }
        return index;
    }

    public int findIndexById(K id) throws IOException {
        int index = -1;
        for(int i=0;i<this.getAll().length;++i){
            if(this.getAll()[i].getId().equals(id)) { index = i; }
        }
        return index;
    }

    public boolean delete(K id) throws IOException {
        int index = findIndexById(id);
        if(index>=0) {
            List<E> list = this.getAllAsList();
            list.remove(index);
            updateDb(list);
            return true;
        }
        return false;
    }

    protected abstract boolean updateDb(List<E> list);

    public boolean updateDb(E[]es)  {
        try {
            mapper.writeValue(database.getDb(), es);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean create(E entity) throws IOException {
        for(E e : this.getAll())
        {
            if(e.equals(entity)) return false;
            //TODO: generate log record or some kind of error
        }
        try {
            List<E>list = this.getAllAsList();
            list.add(entity);
            updateDb(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public E update(E entity) throws Exception {
        int index = findIndex(entity);
        E[] es = this.getAll();
        if(index>=0)
        {
            if(es[index].equals(entity)) return es[index];
            es[index]=entity;
            updateDb(es);
        }
        else throw new Exception("You cannot update not existing entity!");
        return es[index];
    }
}
