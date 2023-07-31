package com.fayardev.regms.services;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.Profile;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.repositories.ProfileRepository;
import com.fayardev.regms.services.abstracts.IProfileService;
import com.fayardev.regms.util.FileServer;
import com.fayardev.regms.util.IDGenerator;
import com.fayardev.regms.validates.ProfileValidate;
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
    public boolean saveEntity(Profile entity) throws Exception {
        if (!ProfileValidate.profileValidate(entity)) {
            return false;
        }
        if (getEntityByUser(entity.getUser()) == null) {
            var avatarBase64 = entity.getAvatarPath();
            var avatarPath = IDGenerator.mediaNameIDGenerator(IDGenerator.AVATAR);
            entity.setAvatarPath(avatarPath);

            FileServer.uploadAvatar(avatarBase64, avatarPath);
            repository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Profile entity) throws Exception {
        if (!ProfileValidate.profileValidate(entity)) {
            return false;
        }
        repository.save(entity);
        return true;
    }

    @Override
    @Transactional
    public Profile getEntityById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<Profile> getEntities() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public BaseEntity getEntityByUser(User user) {
        return repository.getProfileByUser(user);
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
