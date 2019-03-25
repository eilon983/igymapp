package il.ac.hit;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * Singelton instance for generate SessionFactory
 */
public class HibernateUtilties {

    private static HibernateUtilties ourInstance = new HibernateUtilties();
    private static SessionFactory factory;
    /**
     * get function for hubrante singelton object.
     * @return
     */
    public static HibernateUtilties getInstance() {
        return ourInstance;
    }
    /**
     * Constructor for singelton object.
     */
    private HibernateUtilties()  {
        factory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    /**
     *Create SessionFactory object.
     * @return
     */
    public SessionFactory getSessionFactory() {
        return factory;
    }





}
