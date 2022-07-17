package com.fayardev.regms.repositories.abstracts;

import com.fayardev.regms.entities.BaseEntity;

public interface IUserRepository<T extends BaseEntity> extends IRepository<T> {

    BaseEntity getEntityByEmail(String email);

    BaseEntity getEntityByPhoneNo(String phoneNo);

    BaseEntity getEntityByUsername(String username);
}
