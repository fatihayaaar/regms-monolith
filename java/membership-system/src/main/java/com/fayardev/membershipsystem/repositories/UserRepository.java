package com.fayardev.membershipsystem.repositories;

import com.fayardev.membershipsystem.entities.BaseEntity;
import com.fayardev.membershipsystem.entities.BlankEntity;
import com.fayardev.membershipsystem.entities.User;
import com.fayardev.membershipsystem.repositories.abstracts.IUserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

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
