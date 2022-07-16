package com.fayardev.membershipsystem.services;

import com.fayardev.membershipsystem.entities.BaseEntity;
import com.fayardev.membershipsystem.entities.BlankEntity;
import com.fayardev.membershipsystem.entities.User;
import com.fayardev.membershipsystem.exceptions.UserException;
import com.fayardev.membershipsystem.exceptions.enums.ErrorComponents;
import com.fayardev.membershipsystem.repositories.UserRepository;
import com.fayardev.membershipsystem.services.abstracts.IUserService;
import com.fayardev.membershipsystem.validates.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class UserService extends BaseService<User> implements IUserService<User> {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public boolean add(User entity) throws Exception {
        entity.setUsername(entity.getUsername().trim().toLowerCase());
        if (!UserValidate.userValidate(entity)) {
            return false;
        }
        if (!emailControl(entity) || !usernameControl(entity)) {
            return false;
        }
        entity.setCreateDate(new Date());
        entity.setActive(true);
        entity.setConfirm(false);
        entity.setVerified(false);
        return repository.add(entity);
    }

    private boolean emailControl(User user) throws UserException {
        BaseEntity userLocal = repository.getEntityByEmail(user.getEmailAddress());
        if (userLocal.getID() != -1) {
            return UserValidate.emailEquals(user.getEmailAddress(), ((User) userLocal).getEmailAddress(), ErrorComponents.EMAIL);
        }
        return true;
    }

    private boolean usernameControl(User user) throws UserException {
        BaseEntity userLocal = repository.getEntityByUsername(user.getUsername());
        if (userLocal.getID() != -1) {
            return UserValidate.usernameEquals(user.getUsername(), ((User) userLocal).getUsername(), ErrorComponents.USERNAME);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean delete(int id) throws Exception {
        return repository.delete(id);
    }

    @Override
    @Transactional
    public boolean update(User entity) throws UserException {
        return repository.update(entity);
    }

    @Override
    @Transactional
    public User getEntityById(int id) {
        return repository.getEntityById(id);
    }

    @Override
    @Transactional
    public List<User> getEntities() {
        return repository.getEntities();
    }

    @Override
    @Transactional
    public BaseEntity getEntityByEmail(String email) {
        return repository.getEntityByEmail(email);
    }

    @Override
    @Transactional
    public BaseEntity getEntityByUsername(String username) {
        return repository.getEntityByUsername(username);
    }

    @Override
    @Transactional
    public BaseEntity getEntityByPhoneNo(String phoneNo) {
        return repository.getEntityByPhoneNo(phoneNo);
    }

    @Override
    @Transactional
    public boolean changeUsername(User user) throws UserException {
        var username = user.getUsername();
        if (!UserValidate.strUsernameLengthValidate(username)) {
            return false;
        }
        if (!UserValidate.usernameRegexValidate(username)) {
            return false;
        }
        BaseEntity entity = this.getEntityByUsername(username);
        if (entity instanceof BlankEntity) {
            return this.update(user);
        }
        return false;
    }
}