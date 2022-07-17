package com.fayardev.membershipsystem.services;

import com.fayardev.membershipsystem.entities.BaseEntity;
import com.fayardev.membershipsystem.services.abstracts.IService;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService<T extends BaseEntity> implements IService<T> {

    protected BaseService() {
    }
}