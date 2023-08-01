package com.fayardev.regms.repositories;

import com.fayardev.regms.entities.PasswordReset;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PasswordResetRepository extends BaseRepository<PasswordReset, Long> {

    @Transactional
    PasswordReset findByValidateCode(String token);

    @Transactional
    PasswordReset findByEmail(String emailAddress);

    @Transactional
    PasswordReset findByTokenPassword(String token);
}
