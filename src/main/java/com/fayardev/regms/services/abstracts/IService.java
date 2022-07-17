package com.fayardev.regms.services.abstracts;

import com.fayardev.regms.entities.BaseEntity;

import java.util.List;

public interface IService<T extends BaseEntity> {

    boolean add(T entity) throws Exception;

    boolean delete(int id) throws Exception;

    boolean update(T entity) throws Exception;

    T getEntityById(int id);

    List<T> getEntities();
}

