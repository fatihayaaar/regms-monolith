package com.fayardev.membershipsystem.services;

import com.fayardev.membershipsystem.entities.BaseEntity;
import com.fayardev.membershipsystem.entities.User;
import com.fayardev.membershipsystem.repositories.UserRepository;
import com.fayardev.membershipsystem.services.abstracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return repository.add(entity);
    }

    @Override
    @Transactional
    public boolean delete(int id) throws Exception {
        return repository.delete(id);
    }

    @Override
    @Transactional
    public boolean update(User entity) throws Exception {
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
}