package by.bsu.ilyin.dao;

import by.bsu.ilyin.dbc.DBC;
import by.bsu.ilyin.entities.IdEntity;
import by.bsu.ilyin.exceptions.ControllerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public abstract class Controller<E extends IdEntity,K> {

    DBC dbc;
    //Statement statement;
    //Connection connection;
    ObjectMapper mapper;
    Database database;
    Converter converter;
    Logger logger = LogManager.getLogger();

    public Controller() throws SQLException, ClassNotFoundException {
        dbc = new DBC();
    }

    public abstract E[] getAll() throws IOException, SQLException, ClassNotFoundException, JSONException;
    public abstract boolean updateDb(List<E> list);

    public abstract List<E> getAllAsList() throws SQLException, ClassNotFoundException, JSONException, IOException;

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

    public int findIndex(E e) throws IOException, SQLException, JSONException, ClassNotFoundException {
        int index = -1;
        for(int i=0;i<this.getAll().length;++i){
            if(this.getAll()[i].getId()==e.getId()) { index = i; }
        }
        return index;
    }

    public int findIndexById(K id) throws IOException, SQLException, JSONException, ClassNotFoundException {
        int index = -1;
        for(int i=0;i<this.getAll().length;++i){
            if(this.getAll()[i].getId().equals(id)) { index = i; }
        }
        return index;
    }

    public boolean delete(K id) throws IOException, SQLException, JSONException, ClassNotFoundException, ControllerException {
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

    public boolean delete(E entity) throws IOException, SQLException, JSONException, ClassNotFoundException, ControllerException {
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

    public abstract boolean create(E entity) throws SQLException, ControllerException, ClassNotFoundException;
//    {
//        for(E e : this.getAll())
//        {
//            if(e.equals(entity)) {
//                logger.warn("Entity having this ID already exists");
//                return false;
//            }
//        }
//        try {
//            List<E>list = this.getAllAsList();
//            if(Objects.isNull(entity.getId())) entity.setId(determineIdBeforeAdding(list));
//            list.add(entity);
//            updateDb(list);
//        } catch (IOException e) {
//            logger.error(e.getMessage());
//        }
//        logger.info("Entity was created");
//        return true;
//    }

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

    public Integer determineIdBeforeAdding(List<E>list){
        if(list.isEmpty()) return 1;
        Integer max=list.get(0).getId();
        for(E e : list){
            if(e.getId()>max) max = e.getId();
        }
        return max+1;
    }

    public E getByName(String name){return null;}
    public E getByEmail(String email){return null;}
}
