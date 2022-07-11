package com.fayardev.membershipsystem.services.abstracts;

import com.fayardev.membershipsystem.entities.BaseEntity;

public interface IUserService<T extends BaseEntity> extends IService<T> {

    BaseEntity getEntityByEmail(String email);

    BaseEntity getEntityByUsername(String username);

    BaseEntity getEntityByPhoneNo(String phoneNo);
}
