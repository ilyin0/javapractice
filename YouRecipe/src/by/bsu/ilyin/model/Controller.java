package by.bsu.ilyin.model;

import by.bsu.ilyin.entities.IdEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public abstract class Controller<E extends IdEntity,K> {

    ObjectMapper mapper;
    Database database;
    Converter converter;
    Logger logger = LogManager.getLogger();

    public abstract E[] getAll() throws IOException;
    public abstract boolean updateDb(List<E> list);

    public List<E> getAllAsList() throws IOException {
        return this.converter.fromJSONFileToList(this.database.getDb());
    }

    public E getById(K id) throws Exception{
        try {
            E[] es = this.getAll();
            for (E e : es) {
                if (id.equals(e.getId())) return e;
            }
            throw new Exception("This entity doesn't exist!");
        }
        catch(Exception exception) {
            logger.error(exception.getMessage());
        }
        return null;
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
            logger.info("Entity was deleted");
            return true;
        }
        logger.warn("Entity wasn't deleted");
        return false;
    }

    public boolean delete(E entity) throws IOException {
        return delete((K) entity.getId());
    }

    public boolean updateDb(E[]es)  {
        try {
            mapper.writeValue(database.getDb(), es);
            return true;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean create(E entity) throws IOException {
        for(E e : this.getAll())
        {
            if(e.equals(entity)) {
                logger.warn("Entity having this ID already exists");
                return false;
            }
        }
        try {
            List<E>list = this.getAllAsList();
            list.add(entity);
            updateDb(list);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        logger.info("Entity was created");
        return true;
    }

    public boolean update(E entity) throws Exception {
        try {
            int index = findIndex(entity);
            E[] es = this.getAll();
            if (index >= 0) {
                if (es[index].equals(entity)) {
                    logger.warn("Entity wasn't updated");
                    return false;
                }
                es[index] = entity;
                updateDb(es);
                logger.info("Entity was updated");
                return true;
            }
            throw new Exception("You cannot update not existing entity!");
        }
        catch(Exception e) {
            logger.warn(e.getMessage());
        }
        return false;
    }

    public E getByName(String name){return null;}
    public E getByEmail(String email){return null;}
}
