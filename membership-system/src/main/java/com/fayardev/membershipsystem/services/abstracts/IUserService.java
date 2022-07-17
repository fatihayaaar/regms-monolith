package com.fayardev.membershipsystem.services.abstracts;

import com.fayardev.membershipsystem.entities.BaseEntity;
import com.fayardev.membershipsystem.entities.User;
import com.fayardev.membershipsystem.exceptions.UserException;

import javax.transaction.Transactional;

public interface IUserService<T extends BaseEntity> extends IService<T> {

    BaseEntity getEntityByEmail(String email);

    BaseEntity getEntityByUsername(String username);

    BaseEntity getEntityByPhoneNo(String phoneNo);

    @Transactional
    boolean changeUsername(User user) throws UserException;

    @Transactional
    boolean changeEmailAddress(User user) throws UserException;

    @Transactional
    boolean changePhoneNo(User user) throws UserException;
}
