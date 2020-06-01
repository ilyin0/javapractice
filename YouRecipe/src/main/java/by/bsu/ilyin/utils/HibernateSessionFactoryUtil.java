package by.bsu.ilyin.utils;

import by.bsu.ilyin.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    public static Logger logger = LogManager.getLogger();

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure(new File("src\\main\\resources\\META-INF\\entities.cfg.xml"));
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Product.class);
                configuration.addAnnotatedClass(Recipe.class);
                configuration.addAnnotatedClass(Step.class);
                configuration.addAnnotatedClass(UnitOfRecipe.class);
                StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
                serviceRegistryBuilder.applySettings(configuration.getProperties());
                ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
