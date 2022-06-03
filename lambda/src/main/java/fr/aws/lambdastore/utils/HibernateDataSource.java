package fr.aws.lambdastore.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

/**
 *
 * @param <T>
 */
public class HibernateDataSource<T> {

    private Session session;
    private Query<T> query;

    /**
     *
     * @param queryStatement
     * @param entity
     * @return
     */
    public boolean prepareQuery(String queryStatement, T entity) {

        boolean result = true;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            query = (Query<T>) sessionFactory.openSession().createQuery(queryStatement, entity.getClass());

        } catch (NoResultException e) {
            result = false;
            e.printStackTrace();
        }

        return result;
    }

    /**
     *
     * @return
     */
    public Query<T> getQuery() {
        return query;
    }

    /**
     *
     * @return
     */
    public T executeReadQuery() {

        T entity = null;

        if(query == null || session == null) {
            return null;
        }

        try {
            entity = (T) query.getSingleResult();

        } catch (NoResultException nre) {
            nre.printStackTrace();
        }

        return entity;
    }

    /**
     *
     * @param entity
     */
    public void executeWriteQuery(T entity) {

        if(query == null || session == null) {
            return;
        }

        session.saveOrUpdate(entity);
    }

    /**
     *
     * @param entity
     */
    public void executeWriteQueryWithFeedback(T entity) {

        if(query == null || session == null) {
            return;
        }

        Long Id = (Long) session.save(entity);
        entity = (T) session.get(entity.getClass(), Id);
    }

    /**
     *
     */
    public void close() {

        if(session == null) {
            return;
        }

        session.getTransaction().commit();
    }

}
