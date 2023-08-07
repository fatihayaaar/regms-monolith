package com.fayardev.regms.services;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.BlankEntity;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.exceptions.UserException;
import com.fayardev.regms.exceptions.enums.ErrorComponents;
import com.fayardev.regms.repositories.UserRepository;
import com.fayardev.regms.services.abstracts.IUserService;
import com.fayardev.regms.validates.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class UserService extends BaseService<User> implements IUserService<User>, UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public boolean saveEntity(User entity) throws Exception {
        entity.setUsername(entity.getUsername().trim().toLowerCase());
        if (!UserValidate.userValidate(entity)) {
            return false;
        }
        if (!emailControl(entity) || !usernameControl(entity)) {
            return false;
        }
        entity.setActive(true);
        entity.setConfirm(false);
        entity.setVerified(false);
        repository.save(entity);
        return true;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User applicationUser = repository.getUserByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

    private boolean emailControl(User user) throws UserException {
        User userLocal = repository.getUserByEmailAddress(user.getEmailAddress());
        if (userLocal != null) {
            return UserValidate.emailEquals(user.getEmailAddress(), userLocal.getEmailAddress(), ErrorComponents.EMAIL);
        }
        return true;
    }

    private boolean usernameControl(User user) throws UserException {
        User userLocal = repository.getUserByUsername(user.getUsername());
        if (userLocal != null) {
            return UserValidate.usernameEquals(user.getUsername(), userLocal.getUsername(), ErrorComponents.USERNAME);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean update(User entity) throws UserException {
        repository.save(entity);
        return true;
    }

    @Override
    @Transactional
    public User getEntityById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<User> getEntities() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public BaseEntity getEntityByEmail(String email) {
        return repository.getUserByEmailAddress(email);
    }

    @Override
    @Transactional
    public BaseEntity getEntityByUsername(String username) {
        return repository.getUserByUsername(username);
    }

    @Override
    @Transactional
    public BaseEntity getEntityByPhoneNo(String phoneNo) {
        return repository.getUserByPhoneNo(phoneNo);
    }

    @Override
    @Transactional
    public boolean changeUsername(User user) throws UserException {
        var username = user.getUsername().trim().toLowerCase();
        user.setUsername(username);
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

    @Override
    @Transactional
    public boolean changeEmailAddress(User user) throws UserException {
        var emailAddress = user.getEmailAddress().trim();
        if (!UserValidate.emailLengthValidate(emailAddress)) {
            return false;
        }
        if (!UserValidate.emailRegexValidate(emailAddress)) {
            return false;
        }
        BaseEntity entity = this.getEntityByEmail(emailAddress);
        if (entity instanceof BlankEntity) {
            return this.update(user);
        }
        return false;
    }

    @Override
    @Transactional
    public boolean changePhoneNo(User user) throws UserException {
        var phoneNo = user.getPhoneNo().trim();

        BaseEntity entity = this.getEntityByPhoneNo(phoneNo);
        if (entity instanceof BlankEntity) {
            return this.update(user);
        }
        return false;
    }

    @Override
    @Transactional
    public boolean freeze(User user) throws UserException {
        user.setActive(!user.isActive());
        return this.update(user);
    }

}