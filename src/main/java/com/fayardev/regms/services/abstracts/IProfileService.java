package com.fayardev.regms.services.abstracts;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.Profile;
import com.fayardev.regms.entities.User;

import javax.transaction.Transactional;

public interface IProfileService<T extends BaseEntity> extends IService<T> {
    @Transactional
    BaseEntity getEntityByUser(User user);

    @Transactional
    boolean changeAboutMe(Profile profile) throws Exception;

    @Transactional
    boolean updateAvatar(Profile profile) throws Exception;

    @Transactional
    boolean deleteAvatar(Profile profile) throws Exception;
}
