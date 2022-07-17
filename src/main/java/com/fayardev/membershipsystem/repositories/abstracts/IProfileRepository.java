package com.fayardev.membershipsystem.repositories.abstracts;

import com.fayardev.membershipsystem.entities.BaseEntity;
import com.fayardev.membershipsystem.entities.User;

import javax.transaction.Transactional;

public interface IProfileRepository<T extends BaseEntity> extends IRepository<T> {
    @Transactional
    BaseEntity getEntityByUser(User user);
}
