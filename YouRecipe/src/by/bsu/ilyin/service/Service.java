package by.bsu.ilyin.service;

import by.bsu.ilyin.entities.IdEntity;
import by.bsu.ilyin.model.Controller;

import java.io.IOException;
import java.util.List;

public abstract class Service<E extends IdEntity,K> {
    Controller<E,K> controller;

    public boolean updateDb(List<E>list){
        return controller.updateDb(list);
    }
    public  E[] getAll() throws IOException {
        return controller.getAll();
    }
    public E getById(K id) throws Exception {
        return controller.getById(id);
    }
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
    public E getByName(String name){return null;}

}
