package com.fayardev.regms.services;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.services.abstracts.IService;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService<T extends BaseEntity> implements IService<T> {

    protected BaseService() {
    }
}