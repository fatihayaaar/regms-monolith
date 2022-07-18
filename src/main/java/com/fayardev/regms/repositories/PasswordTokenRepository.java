package com.fayardev.regms.repositories;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.BlankEntity;
import com.fayardev.regms.entities.PasswordResetToken;
import com.fayardev.regms.repositories.abstracts.IPasswordTokenRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PasswordTokenRepository extends BaseRepository<PasswordResetToken> implements IPasswordTokenRepository<PasswordResetToken> {

    @Autowired
    public PasswordTokenRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    @Transactional
    public boolean numberOfInteractionsInc(PasswordResetToken entity) {
        entity.setNumberOfInteractions(entity.getNumberOfInteractions() + 1);
        entity.setActive(entity.getNumberOfInteractions() <= 7 && entity.getNumberOfInteractions() >= 0);
        super.session.update(entity);
        return true;
    }

    @Override
    @Transactional
    public BaseEntity findByToken(String token) {
        return super.listToEntity(this.session
                .getNamedQuery("PasswordResetToken.findByToken")
                .setParameter("token", token)
                .list());
    }

    @Override
    @Transactional
    public BaseEntity findByPasswordToken(String token) {
        return super.listToEntity(this.session
                .getNamedQuery("PasswordResetToken.findByTokenPassword")
                .setParameter("tokenPassword", token)
                .list());
    }

    @Override
    @Transactional
    public BaseEntity findByEmail(String email) {
        return super.listToEntity(this.session
                .getNamedQuery("PasswordResetToken.findByEmail")
                .setParameter("emailAddress", email)
                .list());
    }
}
