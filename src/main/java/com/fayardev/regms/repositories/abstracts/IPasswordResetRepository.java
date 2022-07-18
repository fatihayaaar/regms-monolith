package com.fayardev.regms.repositories.abstracts;

import com.fayardev.regms.entities.BaseEntity;

import javax.transaction.Transactional;

public interface IPasswordResetRepository<T extends BaseEntity> extends IRepository<T> {

    @Transactional
    BaseEntity findByValidateCode(String token);

    @Transactional
    BaseEntity findByEmail(String email);

    @Transactional
    BaseEntity findByPasswordToken(String token);
}
