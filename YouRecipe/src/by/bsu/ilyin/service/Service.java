package by.bsu.ilyin.service;

import by.bsu.ilyin.entities.IdEntity;
import by.bsu.ilyin.model.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public abstract class Service<E extends IdEntity,K> {
    Controller<E,K> controller;
    Logger logger = LogManager.getLogger();

    public abstract E[] getAll();
    public abstract boolean updateDb(List<E>list);

    public boolean updateDb(E[]es){
        return controller.updateDb(es);
    }
    public List<E> getAllAsList() throws IOException {
        return controller.getAllAsList();
    }
    public boolean create(E entity) throws IOException {
        return controller.create(entity);
    }
    public boolean update(E entity) throws Exception {
        return controller.update(entity);
    }
    public boolean delete(E entity) throws IOException {
        return controller.delete(entity);
    }

}
