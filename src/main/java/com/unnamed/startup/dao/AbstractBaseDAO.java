package com.unnamed.startup.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@Transactional
public abstract class AbstractBaseDAO<T> {

    private static final Log LOGGER = LogFactory.getLog(AbstractBaseDAO.class);
    private static final int LOCK_TIME_OUT_IN_MILLISECONDS = 600000;

    protected abstract EntityManager getEntityManager();

    public <N extends T> void persist(N object) {
        this.getEntityManager().persist(object);
    }

    public <N extends T> N merge(N object) {
        return this.getEntityManager().merge(object);
    }

    public <N extends T> void remove(N object) {
        this.getEntityManager().remove(object);
    }

    public <N extends T> void refresh(N object) {
        this.getEntityManager().refresh(object);
    }

    public <N extends T> N findById(Class<N> clazz, Object id) {
        return this.getEntityManager().find(clazz, id);
    }

    public <N extends T> N getreference(Class<N> clazz, Object id) {
        return this.getEntityManager().getReference(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public <N extends T> List<N> find(Class<N> clazz) {
        return this.getEntityManager().createQuery("SELECT e FROM " + clazz.getSimpleName() + " e")
                .getResultList();
    }

    public <N extends T> void deleteById(Class<N> clazz, Objects id) {
        this.remove(this.findById(clazz, id));
    }

    public void flush() {
        this.getEntityManager().flush();
    }

    protected <N extends T> Criteria createCriteria(Class<N> clazz) {
        return ((Session) this.getEntityManager().getDelegate()).createCriteria(clazz);
    }

    protected Session getHibernateSession() {
        Object delegate = this.getEntityManager().getDelegate();
        Session session = null;

        if (delegate instanceof Session) {
            session = (Session) delegate;
        } else {
            LOGGER.warn(
                    "The delegate in the EntityManager is not an instance of org.hibernate.Session");
        }

        return session;
    }

    public void lock(T entity) {
        this.lock(entity, LOCK_TIME_OUT_IN_MILLISECONDS);
    }

    public void lock(T entity, int lockTimeOutInMilliseconds) {
        Session session = this.getHibernateSession();
        LockOptions lockOptions = new LockOptions();
        lockOptions.setLockMode(LockMode.PESSIMISTIC_WRITE);
        lockOptions.setTimeOut(lockTimeOutInMilliseconds);
        lockOptions.setScope(false);
        session.buildLockRequest(lockOptions).lock(entity);
    }

    public static int getLockTimeOutInMilliseconds() {
        return LOCK_TIME_OUT_IN_MILLISECONDS;
    }
}























