package com.fayardev.membershipsystem.services.abstracts;

import com.fayardev.membershipsystem.entities.BaseEntity;
import com.fayardev.membershipsystem.entities.Profile;
import com.fayardev.membershipsystem.entities.User;

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
