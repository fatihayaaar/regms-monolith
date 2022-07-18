package com.fayardev.regms.services.abstracts;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.PasswordReset;
import com.fayardev.regms.entities.User;

import javax.transaction.Transactional;

public interface IPasswordResetService<T extends BaseEntity> extends IService<T> {

    @Transactional
    boolean createPasswordResetTokenForUser(User user, String token, String passwordToken) throws Exception;

    @Transactional
    boolean changeUserPassword(User user, String password) throws Exception;

    @Transactional
    BaseEntity getUserByPasswordResetToken(String token);

    @Transactional
    boolean numberOfInteractionsInc(PasswordReset passwordReset) throws Exception;

    @Transactional
    boolean activeCompPassToken(PasswordReset passwordReset) throws Exception;

    @Transactional
    boolean activePasswordToken(PasswordReset passwordReset) throws Exception;

    @Transactional
    BaseEntity getPassTokenByEmail(String email);

    @Transactional
    BaseEntity getUserByTokenPassword(String token);

    @Transactional
    BaseEntity getTokenByPasswordToken(String token);

    @Transactional
    String validatePasswordResetToken(String token, String email);
}
