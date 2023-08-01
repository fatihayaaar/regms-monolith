package com.fayardev.regms.services.abstracts;

import com.fayardev.regms.entities.BaseEntity;

import java.util.List;

public interface IService<T extends BaseEntity> {

    boolean saveEntity(T entity) throws Exception;

    boolean delete(Long id) throws Exception;

    boolean update(T entity) throws Exception;

    T getEntityById(Long id);

    List<T> getEntities();
}

