package com.fayardev.regms.repositories;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.Profile;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.repositories.abstracts.IProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class ProfileRepository extends BaseRepository<Profile> implements IProfileRepository<Profile> {

    @Autowired
    protected ProfileRepository(EntityManager entityManager) {
        super(entityManager);
        super.setClazz(Profile.class);
    }

    @Override
    @Transactional
    public BaseEntity getEntityByUser(User user) {
        return super.listToEntity(this.session
                .getNamedQuery("Profile.findByUser")
                .setParameter("user", user)
                .list());
    }
}
