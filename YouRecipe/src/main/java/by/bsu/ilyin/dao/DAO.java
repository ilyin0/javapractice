package by.bsu.ilyin.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public abstract class DAO<E,K> {

    Logger logger = LogManager.getLogger();

    public DAO() { }

    public abstract E[] getAll();

    public abstract List<E> getAllAsList();

    public abstract E getById(K id);

    public abstract boolean delete(K id);

    public abstract boolean create(E entity);

    public abstract boolean update(E entity);

    public E getByName(String name){return null;}
    public E getByEmail(String email){return null;}
}
