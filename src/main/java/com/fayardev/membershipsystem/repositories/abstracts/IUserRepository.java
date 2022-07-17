package com.fayardev.membershipsystem.repositories.abstracts;

import com.fayardev.membershipsystem.entities.BaseEntity;

public interface IUserRepository<T extends BaseEntity> extends IRepository<T> {

    BaseEntity getEntityByEmail(String email);

    BaseEntity getEntityByPhoneNo(String phoneNo);

    BaseEntity getEntityByUsername(String username);
}
