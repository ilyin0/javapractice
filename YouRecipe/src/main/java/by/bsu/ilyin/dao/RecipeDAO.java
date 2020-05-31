package by.bsu.ilyin.dao;

import by.bsu.ilyin.hibernate.Recipe;
import by.bsu.ilyin.utils.HibernateSessionFactoryUtil;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class RecipeDAO extends DAO<Recipe, Integer> {

    public RecipeDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public Recipe[] getAll()  {
        return this.getAllAsList().toArray(new Recipe[0]);
    }

    @SneakyThrows
    @Override
    public List<Recipe> getAllAsList() {
        return (List<Recipe>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Recipe").list();
    }

    @SneakyThrows
    @Override
    public Recipe getById(Integer id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Recipe.class, id);
    }

    @SneakyThrows
    @Override
    public boolean create(Recipe entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(entity);
            transaction.commit();
            return true;
        }
        catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }
        finally {
            session.close();
        }
        return false;
    }

    @SneakyThrows
    @Override
    public boolean update(Recipe entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(entity);
            transaction.commit();
            return true;
        }
        catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }
        finally {
            session.close();
        }
        return false;
    }

    @SneakyThrows
    @Override
    public boolean delete(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(getById(id));
            transaction.commit();
            return true;
        }
        catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }
        finally {
            session.close();
        }
        return false;
    }

}
