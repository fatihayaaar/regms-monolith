package com.fayardev.regms.repositories;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.PasswordReset;
import com.fayardev.regms.repositories.abstracts.IPasswordResetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class PasswordResetRepository extends BaseRepository<PasswordReset> implements IPasswordResetRepository<PasswordReset> {

    @Autowired
    public PasswordResetRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    @Transactional
    public BaseEntity findByToken(String token) {
        return super.listToEntity(this.session
                .getNamedQuery("PasswordReset.findByToken")
                .setParameter("token", token)
                .list());
    }

    @Override
    @Transactional
    public BaseEntity findByPasswordToken(String token) {
        return super.listToEntity(this.session
                .getNamedQuery("PasswordReset.findByTokenPassword")
                .setParameter("tokenPassword", token)
                .list());
    }

    @Override
    @Transactional
    public BaseEntity findByEmail(String email) {
        return super.listToEntity(this.session
                .getNamedQuery("PasswordReset.findByEmail")
                .setParameter("emailAddress", email)
                .list());
    }
}
