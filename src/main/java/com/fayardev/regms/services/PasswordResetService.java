package com.fayardev.regms.services;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.BlankEntity;
import com.fayardev.regms.entities.PasswordReset;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.repositories.PasswordResetRepository;
import com.fayardev.regms.repositories.UserRepository;
import com.fayardev.regms.services.abstracts.IPasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class PasswordResetService extends BaseService<PasswordReset> implements IPasswordResetService<PasswordReset> {

    private final PasswordResetRepository repository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PasswordResetService(PasswordResetRepository repository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public boolean add(PasswordReset entity) throws Exception {
        return repository.add(entity);
    }

    @Override
    @Transactional
    public boolean delete(int id) throws Exception {
        return false;
    }

    @Override
    @Transactional
    public boolean update(PasswordReset entity) throws Exception {
        return repository.update(entity);
    }

    @Override
    @Transactional
    public PasswordReset getEntityById(int id) {
        return null;
    }

    @Override
    @Transactional
    public List<PasswordReset> getEntities() {
        return null;
    }

    @Override
    @Transactional
    public boolean createPasswordResetTokenForUser(User user, String token, String passwordToken) throws Exception {
        PasswordReset myToken = new PasswordReset(token, user);
        myToken.setExpiryDate(new Date());
        myToken.setActive(true);
        myToken.setEmailAddress(user.getEmailAddress());
        myToken.setNumberOfInteractions(0);
        myToken.setTokenPassword(passwordToken);
        myToken.setActiveTokenPassword(true);

        return this.add(myToken);
    }

    @Override
    @Transactional
    public boolean changeUserPassword(User user, String password) throws Exception {
        user.setHashPassword(bCryptPasswordEncoder.encode(password));
        return this.userRepository.update(user);
    }

    @Override
    @Transactional
    public BaseEntity getUserByPasswordResetToken(String token) {
        return repository.findByToken(token);
    }

    @Override
    @Transactional
    public boolean numberOfInteractionsInc(PasswordReset passwordReset) throws Exception {
        passwordReset.setNumberOfInteractions(passwordReset.getNumberOfInteractions() + 1);
        passwordReset.setActive(passwordReset.getNumberOfInteractions() <= 7 && passwordReset.getNumberOfInteractions() >= 0);
        return this.update(passwordReset);
    }

    @Override
    @Transactional
    public boolean activeCompPassToken(PasswordReset passwordReset) throws Exception {
        passwordReset.setActive(false);
        return this.update(passwordReset);
    }

    @Override
    @Transactional
    public boolean activePasswordToken(PasswordReset passwordReset) throws Exception {
        passwordReset.setActiveTokenPassword(false);
        return this.update(passwordReset);
    }

    @Override
    @Transactional
    public BaseEntity getPassTokenByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional
    public BaseEntity getUserByTokenPassword(String token) {
        BaseEntity entity = repository.findByPasswordToken(token);
        if (entity.getID() == -1) {
            return new BlankEntity();
        }
        return userRepository.getEntityById(((PasswordReset) entity).getUser().getID());
    }

    @Override
    @Transactional
    public BaseEntity getTokenByPasswordToken(String token) {
        return repository.findByPasswordToken(token);
    }
}

