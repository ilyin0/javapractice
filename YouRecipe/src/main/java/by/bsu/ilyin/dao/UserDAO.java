package by.bsu.ilyin.dao;

import by.bsu.ilyin.entities.User;
import by.bsu.ilyin.utils.HibernateSessionFactoryUtil;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAO extends DAO<User, Long> {

    public UserDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public User[] getAll() {
        return null;
    }

    @SneakyThrows
    @Override
    public List<User> getAllAsList()  {
        return (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM User").list();
    }


    @SneakyThrows
    @Override
    public User getById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    @Override
    public User getByEmail(String email){
        return (User) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM User WHERE \"email\"="+email);
    }

    @Override
    public boolean create(User entity)  {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(entity);
            transaction.commit();
            return true;
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
        session.close();
        return false;
    }

    @Override
    public boolean update(User entity)  {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
            transaction.commit();
            return true;
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
        finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Long id)  {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(getById(id));
            transaction.commit();
            return true;
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
        finally {
            session.close();
        }
        return false;
    }
}
