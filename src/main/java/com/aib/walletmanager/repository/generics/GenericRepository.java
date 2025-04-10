package com.aib.walletmanager.repository.generics;

import com.aib.walletmanager.connectorFactory.Connector;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

public class GenericRepository<T, ID> {

    private final Class<T> entity;
    private final Connector sessionConnector = Connector.getInstance();

    public GenericRepository(Class<T> entity) {
        this.entity = entity;
    }

    public void save(T entity) {
        Transaction transaction = null;
        try (Session session = sessionConnector.getMainSession().openSession()) {
            transaction = session.beginTransaction();
            Object id = getEntityId(entity);
            if (id == null) {
                session.persist(entity);
            } else {
                session.merge(entity);
            }
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void saveAll(Collection<T> collection) {
        Transaction transaction = null;
        try (Session session = sessionConnector.getMainSession().openSession()) {
            transaction = session.beginTransaction();
            int batchSize = 20;
            int countBatches = 0;
            for (T entity : collection) {
                Object id = getEntityId(entity);
                if (id == null) {
                    session.persist(entity);
                } else {
                    session.merge(entity);
                }
                if (++countBatches % batchSize == 0) {
                    session.flush();
                    session.clear();
                }
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Consider logging instead
        }
    }

    public List<T> findAll() {
        try (Session session = sessionConnector.getMainSession().openSession()) {
            return session.createQuery("from " + entity.getName(), entity).list();
        }
    }

    private Object getEntityId(T entity) {
        try {
            for (Field field : entity.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(jakarta.persistence.Id.class)) {
                    field.setAccessible(true);
                    return field.get(entity);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
