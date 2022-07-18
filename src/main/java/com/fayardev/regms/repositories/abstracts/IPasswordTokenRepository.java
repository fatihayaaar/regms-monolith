package com.fayardev.regms.repositories.abstracts;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.PasswordResetToken;

public interface IPasswordTokenRepository<T extends BaseEntity> extends IRepository<T> {

    BaseEntity findByToken(String token);

    boolean numberOfInteractionsInc(PasswordResetToken entity);

    BaseEntity findByEmail(String email);

    BaseEntity findByPasswordToken(String token);
}
