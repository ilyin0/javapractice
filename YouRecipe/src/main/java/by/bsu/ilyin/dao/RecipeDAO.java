package by.bsu.ilyin.dao;

import by.bsu.ilyin.hibernate.Recipe;
import by.bsu.ilyin.utils.HibernateSessionFactoryUtil;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.List;

@Repository
public class RecipeDAO extends DAO<Recipe, Long> {

    public RecipeDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public Recipe[] getAll()  {
        return (Recipe[]) this.getAllAsList().toArray(new Recipe[0]);
    }

    @SneakyThrows
    @Override
    public List getAllAsList() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Recipe> cq = cb.createQuery(Recipe.class);
        Root<Recipe> rootEntry = cq.from(Recipe.class);
        CriteriaQuery<Recipe> all = cq.select(rootEntry);

        TypedQuery<Recipe> allQuery = session.createQuery(all);
        return allQuery.getResultList();
        //return (List) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Recipe").list();
    }

    @SneakyThrows
    @Override
    public Recipe getById(Long id) {
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
    public boolean delete(Long id) {
        System.out.println("\n\n\nDelete is working\n\n\n");
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        System.out.println("\n\n\nSession: " + session);
        Transaction transaction = session.beginTransaction();
        System.out.println("\n\n\nTransaction: " + transaction);
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
