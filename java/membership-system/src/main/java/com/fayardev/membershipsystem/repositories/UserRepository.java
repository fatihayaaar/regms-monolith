package com.fayardev.membershipsystem.repositories;

import com.fayardev.membershipsystem.entities.BaseEntity;
import com.fayardev.membershipsystem.entities.User;
import com.fayardev.membershipsystem.repositories.abstracts.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class UserRepository extends BaseRepository<User> implements IUserRepository<User> {

    @Autowired
    protected UserRepository(EntityManager entityManager) {
        super(entityManager);
        super.setClazz(User.class);
    }

    @Override
    @Transactional
    public BaseEntity getEntityByEmail(String email) {
        return null;
    }

    @Override
    @Transactional
    public BaseEntity getEntityByPhoneNo(String phoneNo) {
        return null;
    }

    @Override
    @Transactional
    public BaseEntity getEntityByUsername(String username) {
        return null;
    }
}
