package fr.aws.lambdastore.utils;

import fr.aws.lambdastore.product.model.Product;
import fr.aws.lambdastore.variant.model.Variant;
import fr.aws.lambdastore.vendor.model.Vendor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, System.getenv("DB_URL"));
                settings.put(Environment.USER,System.getenv("DB_USERNAME"));
                settings.put(Environment.PASS, System.getenv("DB_PASSWORD"));
                settings.put(Environment.SHOW_SQL, "true");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Vendor.class);
                configuration.addAnnotatedClass(Product.class);
                configuration.addAnnotatedClass(Variant.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println("hibernate Service catch: "+ e.getMessage());
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
