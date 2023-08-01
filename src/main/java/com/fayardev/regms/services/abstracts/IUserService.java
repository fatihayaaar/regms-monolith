package com.fayardev.regms.services.abstracts;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.exceptions.UserException;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;

public interface IUserService<T extends BaseEntity> extends IService<T> {

    BaseEntity getEntityByEmail(String email);

    BaseEntity getEntityByUsername(String username);

    BaseEntity getEntityByPhoneNo(String phoneNo);

    @Transactional
    UserDetails loadUserByUsername(String username);

    @Transactional
    boolean changeUsername(User user) throws UserException;

    @Transactional
    boolean changeEmailAddress(User user) throws UserException;

    @Transactional
    boolean changePhoneNo(User user) throws UserException;

    @Transactional
    boolean freeze(User user) throws UserException;
}
