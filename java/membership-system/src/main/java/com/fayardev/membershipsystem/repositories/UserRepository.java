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

    private final Session session;

    @Autowired
    protected UserRepository(EntityManager entityManager) {
        super(entityManager);
        super.setClazz(User.class);
        session = entityManager.unwrap(Session.class);
    }

    @Override
    @Transactional
    public BaseEntity getEntityByEmail(String email) {
        return listToEntity(this.session
                .getNamedQuery("User.findByEmailAddress")
                .setParameter("email", email)
                .list());
    }

    @Override
    @Transactional
    public BaseEntity getEntityByPhoneNo(String phoneNo) {
        return listToEntity(this.session
                .getNamedQuery("User.findByPhoneNo")
                .setParameter("phoneNo", phoneNo)
                .list());
    }

    @Override
    @Transactional
    public BaseEntity getEntityByUsername(String username) {
        return listToEntity(this.session
                .getNamedQuery("User.findByUsername")
                .setParameter("username", username)
                .list());
    }

    private BaseEntity listToEntity(List entities) {
        if (!entities.isEmpty()) {
            return (BaseEntity) entities.get(0);
        }
        return new BlankEntity();
    }
}
