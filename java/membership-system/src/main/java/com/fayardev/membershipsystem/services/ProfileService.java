package com.fayardev.membershipsystem.services;

import com.fayardev.membershipsystem.entities.BaseEntity;
import com.fayardev.membershipsystem.entities.Profile;
import com.fayardev.membershipsystem.entities.User;
import com.fayardev.membershipsystem.exceptions.ProfileException;
import com.fayardev.membershipsystem.repositories.ProfileRepository;
import com.fayardev.membershipsystem.services.abstracts.IProfileService;
import com.fayardev.membershipsystem.util.FileServer;
import com.fayardev.membershipsystem.util.IDGenerator;
import com.fayardev.membershipsystem.validates.ProfileValidate;
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
        if (!this.profileValidate(entity)) {
            return false;
        }
        var avatarBase64 = entity.getAvatarPath();
        var avatarPath = IDGenerator.mediaNameIDGenerator(IDGenerator.AVATAR);
        entity.setAvatarPath(avatarPath);

        FileServer.uploadAvatar(avatarBase64, avatarPath);
        return repository.add(entity);
    }

    private boolean profileValidate(Profile profile) throws ProfileException {
        if (ProfileValidate.aboutMeValidate(profile.getAboutMe())) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean delete(int id) throws Exception {
        return repository.delete(id);
    }

    @Override
    @Transactional
    public boolean update(Profile entity) throws Exception {
        if (!this.profileValidate(entity)) {
            return false;
        }
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

    @Override
    @Transactional
    public BaseEntity getEntityByUser(User user) {
        return repository.getEntityByUser(user);
    }

    @Override
    @Transactional
    public boolean changeAboutMe(Profile profile) throws Exception {
        if (!ProfileValidate.aboutMeValidate(profile.getAboutMe())) {
            return false;
        }
        return this.update(profile);
    }

    @Override
    @Transactional
    public boolean updateAvatar(Profile profile) throws Exception {
        var avatarBase64 = profile.getAvatarPath();
        var avatarPath = IDGenerator.mediaNameIDGenerator(IDGenerator.AVATAR);

        profile.setAvatarPath(avatarPath);
        FileServer.uploadAvatar(avatarBase64, avatarPath);

        return this.update(profile);
    }

    @Override
    @Transactional
    public boolean deleteAvatar(Profile profile) throws Exception {
        profile.setAvatarPath("");
        return this.update(profile);
    }
}
