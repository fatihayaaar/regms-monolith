package com.fayardev.regms.repositories;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.repositories.abstracts.IUserRepository;
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
        return super.listToEntity(this.session
                .getNamedQuery("User.findByEmailAddress")
                .setParameter("email", email)
                .list());
    }

    @Override
    @Transactional
    public BaseEntity getEntityByPhoneNo(String phoneNo) {
        return super.listToEntity(this.session
                .getNamedQuery("User.findByPhoneNo")
                .setParameter("phoneNo", phoneNo)
                .list());
    }

    @Override
    @Transactional
    public BaseEntity getEntityByUsername(String username) {
        return super.listToEntity(this.session
                .getNamedQuery("User.findByUsername")
                .setParameter("username", username)
                .list());
    }
}
