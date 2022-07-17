package com.fayardev.regms.repositories.abstracts;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.User;

import javax.transaction.Transactional;

public interface IProfileRepository<T extends BaseEntity> extends IRepository<T> {
    @Transactional
    BaseEntity getEntityByUser(User user);
}
