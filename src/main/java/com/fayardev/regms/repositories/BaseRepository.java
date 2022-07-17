package com.fayardev.regms.repositories;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.BlankEntity;
import com.fayardev.regms.repositories.abstracts.IRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public abstract class BaseRepository<T extends BaseEntity> implements IRepository<T> {

    protected final EntityManager entityManager;
    private Class<T> clazz;
    protected final Session session;

    protected BaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.session = this.entityManager.unwrap(Session.class);
    }

    public final void setClazz(final Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    @Transactional
    public boolean add(final T entity) {
        try {
            this.session.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean delete(final int id) {
        try {
            T deleteEntity = this.session.get(this.clazz, id);
            this.session.delete(deleteEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean update(final T entity) {
        try {
            this.session.update(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public List<T> getEntities() {
        return this.session.createQuery("from " + this.clazz.getName(), this.clazz).getResultList();
    }

    @Override
    @Transactional
    public T getEntityById(final int id) {
        return this.session.get(this.clazz, id);
    }

    protected BaseEntity listToEntity(List entities) {
        if (!entities.isEmpty()) {
            return (BaseEntity) entities.get(0);
        }
        return new BlankEntity();
    }
}
