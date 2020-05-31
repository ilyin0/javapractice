package by.bsu.ilyin.utils;

import by.bsu.ilyin.hibernate.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    public static Logger logger = LogManager.getLogger();

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Product.class);
                configuration.addAnnotatedClass(Recipe.class);
                configuration.addAnnotatedClass(UnitOfRecipe.class);
                configuration.addAnnotatedClass(Step.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
