package by.bsu.ilyin.utils;

import by.bsu.ilyin.hibernate.*;
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
                System.out.println("\n\n\nGovnoed\n\n\n");
                Configuration configuration = new Configuration().configure(new File("src\\main\\resources\\META-INF\\hibernate.cfg.xml"));
                System.out.println("\n\n\nGovnoed1\n\n\n" + configuration);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Product.class);
                configuration.addAnnotatedClass(Recipe.class);
                configuration.addAnnotatedClass(Step.class);
                configuration.addAnnotatedClass(UnitOfRecipe.class);
                StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
                System.out.println("\n\n\nGovnoed2\n\n\n" + serviceRegistryBuilder);
                serviceRegistryBuilder.applySettings(configuration.getProperties());
                System.out.println("\n\n\nGovnoed3\n\n\n" + serviceRegistryBuilder);
                ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
                System.out.println("\n\n\nGovnoed4\n\n\n" + serviceRegistry);
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("\n\n\nGovnoed5\n\n\n" + sessionFactory);

            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
