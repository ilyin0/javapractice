package by.bsu.ilyin.dao;

import by.bsu.ilyin.dbc.DBC;
import by.bsu.ilyin.entities.IdEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public abstract class DAO<E,K> {

    DBC dbc;
    Logger logger = LogManager.getLogger();

    public DAO() throws SQLException, ClassNotFoundException {
        dbc = new DBC();
    }

    public abstract E[] getAll();

    public abstract List<E> getAllAsList();

    public abstract E getById(K id);

    public abstract boolean delete(K id);

    public abstract boolean create(E entity);

    public abstract boolean update(E entity);

    public E getByName(String name){return null;}
    public E getByEmail(String email){return null;}
}
