package by.bsu.ilyin.service;

import by.bsu.ilyin.dao.DAO;
import by.bsu.ilyin.entities.IdEntity;
import by.bsu.ilyin.exceptions.ControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public abstract class Service<E,K> {
    DAO<E,K> DAO;
    Logger logger = LogManager.getLogger();

    public  E[] getAll()  {
        return DAO.getAll();
    }
    public E getById(K id){
        return DAO.getById(id);
    }

    public List<E> getAllAsList() {
        return DAO.getAllAsList();
    }
    public boolean create(Object entity)  {
        try {
            return DAO.create((E) entity);
        }
        catch(ClassCastException e){
            logger.error(e);
        }
        return false;
    }
    public boolean update(Object entity) {
        try {
            return DAO.update((E) entity);
        }
        catch(ClassCastException e){
            logger.error(e);
        }
        return false;
    }
    public boolean delete(K id) {
        return DAO.delete(id);
    }
    public E getByName(String name){return null;}
    public E getByEmail(String name){return null;}

}
