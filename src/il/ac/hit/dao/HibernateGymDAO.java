package il.ac.hit.dao;

import org.hibernate.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * this class is layer that communicate with the DB
 * @param <T>
 * @param <M>
 **/

public class HibernateGymDAO<T,M> implements IGymDAO<T,M> {

    private SessionFactory sessionFactory;
    /**
     * creating an object after antialiasing the sessionFactory
     * @param cl
     * @param sessionFactory
     */
    public HibernateGymDAO(Class<T> cl, SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        if (sessionFactory == null)
            throw new RuntimeException("Session factory is null!!!");
    }
    /**
     * geteting stream of workouts from the DB.
     * @param cl
     * @param id
     * @return
     * @throws GymExceptionHandeler
     */

    @Override
    public T get(Class<T> cl, M id) throws GymExceptionHandeler{

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
      try {

         tx = session.beginTransaction();
         @SuppressWarnings("unchecked")
         T element = (T) session.get(cl, (Serializable) id);
         tx.commit();
         return element;
      }
      catch (Exception e)
      {
          if (tx!=null) tx.rollback();
          throw new GymExceptionHandeler(e.getMessage(), e.getCause());
      }

    }
    /**
     * save generic object to the DB.
     * @param object
     * @return
     * @throws GymExceptionHandeler
     */
    @Override
    public T save(T object) throws GymExceptionHandeler{
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        return object;
    }
        catch (Exception e)
    {
        if (tx!=null) tx.rollback();
        throw new GymExceptionHandeler(e.getMessage(), e.getCause());
    }
    }

    /**
     * update an object into the DB.
     * @param object
     * @throws GymExceptionHandeler
     */
    @Override
    public void update(T object) throws GymExceptionHandeler{
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
    }
        catch (Exception e)
    {
        if (tx!=null) tx.rollback();
        throw new GymExceptionHandeler(e.getMessage(), e.getCause());
    }
    }

    /**
     * deleting an object from the DB.
     * @param object
     * @throws GymExceptionHandeler
     */
    @Override
    public void delete(T object) throws GymExceptionHandeler{
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        }
        catch (Exception e)
        {
            if (tx!=null) tx.rollback();
            throw new GymExceptionHandeler(e.getMessage(), e.getCause());
        }
    }
    /**
     * get by sql query
     * @param hsql
     * @param params,hsql1
     * @return
     * @throws GymExceptionHandeler
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> query(String hsql, Map<String, Object> params) throws GymExceptionHandeler {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {

            tx = session.beginTransaction();
            Query query = session.createQuery(hsql);
            if (params != null) {
                for (String i : params.keySet()) {
                    query.setParameter(i, params.get(i));
                }
            }

            List<T> result = null;
            if ((hsql.toUpperCase().indexOf("DELETE") == -1)
                    && (hsql.toUpperCase().indexOf("UPDATE") == -1)
                    && (hsql.toUpperCase().indexOf("INSERT") == -1)) {
                result = query.list();
            } else {
            }
          tx.commit();
            return result;


        }
        catch (Exception e)
            {
                if (tx!=null) tx.rollback();
                throw new GymExceptionHandeler(e.getMessage(), e.getCause());
            }

    }
}

