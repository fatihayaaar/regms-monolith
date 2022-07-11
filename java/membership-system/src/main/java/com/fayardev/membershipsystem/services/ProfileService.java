package com.fayardev.membershipsystem.services;

import com.fayardev.membershipsystem.entities.Profile;
import com.fayardev.membershipsystem.repositories.ProfileRepository;
import com.fayardev.membershipsystem.services.abstracts.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProfileService extends BaseService<Profile> implements IProfileService<Profile> {

    private final ProfileRepository repository;

    @Autowired
    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public boolean add(Profile entity) throws Exception {
        return repository.add(entity);
    }

    @Override
    @Transactional
    public boolean delete(int id) throws Exception {
        return repository.delete(id);
    }

    @Override
    @Transactional
    public boolean update(Profile entity) throws Exception {
        return repository.update(entity);
    }

    @Override
    @Transactional
    public Profile getEntityById(int id) {
        return repository.getEntityById(id);
    }

    @Override
    @Transactional
    public List<Profile> getEntities() {
        return repository.getEntities();
    }
}
