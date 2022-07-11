package com.fayardev.membershipsystem.repositories;

import com.fayardev.membershipsystem.entities.Profile;
import com.fayardev.membershipsystem.repositories.abstracts.IProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ProfileRepository extends BaseRepository<Profile> implements IProfileRepository<Profile> {

    @Autowired
    protected ProfileRepository(EntityManager entityManager) {
        super(entityManager);
        super.setClazz(Profile.class);
    }
}
