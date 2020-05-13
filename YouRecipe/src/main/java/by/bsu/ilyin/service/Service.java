package by.bsu.ilyin.service;

import by.bsu.ilyin.dao.Controller;
import by.bsu.ilyin.entities.IdEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public abstract class Service<E extends IdEntity,K> {
    Controller<E,K> controller;
    Logger logger = LogManager.getLogger();

    public boolean updateDb(List<E>list){
        return controller.updateDb(list);
    }
    public  E[] getAll() throws IOException, SQLException, JSONException, ClassNotFoundException {
        return controller.getAll();
    }
    public E getById(K id) throws Exception {
        return controller.getById(id);
    }
    public boolean updateDb(E[]es){
        return controller.updateDb(es);
    }

    public List<E> getAllAsList() throws IOException, SQLException, JSONException, ClassNotFoundException {
        return controller.getAllAsList();
    }
    public boolean create(Object entity) throws IOException, SQLException, JSONException, ClassNotFoundException {
        try {
            return controller.create((E) entity);
        }
        catch(ClassCastException e){
            logger.error(e);
        }
        return false;
    }
    public boolean update(Object entity) throws Exception {
        try {
            return controller.update((E) entity);
        }
        catch(ClassCastException e){
            logger.error(e);
        }
        return false;
    }
    public boolean delete(E entity) throws IOException, SQLException, JSONException, ClassNotFoundException {
        return controller.delete(entity);
    }
    public boolean delete(K id) throws IOException, SQLException, JSONException, ClassNotFoundException {
        return controller.delete(id);
    }
    public E getByName(String name){return null;}
    public E getByEmail(String name){return null;}

}
