package com.fayardev.regms.util.temptoken;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.PasswordReset;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.services.PasswordResetService;
import com.fayardev.regms.util.mail.Mail;
import com.fayardev.regms.util.temptoken.abstracts.IValidationToken;
import org.springframework.mail.MailSender;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class ValidationToken implements IValidationToken {

    private static ValidationToken validationToken;

    private ValidationToken() {
    }

    public static ValidationToken getInstance() {
        if (validationToken == null) {
            validationToken = new ValidationToken();
        }
        return validationToken;
    }

    @Override
    public boolean sendForgotPasswordValidationCode(MailSender mailSender, PasswordResetService service, User user) throws Exception {
        BaseEntity passwordResetToken = service.getPassTokenByEmail(user.getEmailAddress());

        String tokenPass = UUID.randomUUID().toString();
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        String validationCode = String.format("%06d", number);

        if (passwordResetToken.getID() != -1) {
            PasswordReset passwordReset1 = (PasswordReset) passwordResetToken;
            if (!passwordReset1.isActive() || (new Date().getTime() - passwordReset1.getExpiryDate().getTime()) / 1000 > 120) {
                //Mail.sendPasswordResetValidationCode(mailSender, validationCode, user.getEmailAddress());
                return service.createPasswordResetTokenForUser(user, validationCode, tokenPass);
            }
        } else {
            //Mail.sendPasswordResetValidationCode(mailSender, validationCode, user.getEmailAddress());
            return service.createPasswordResetTokenForUser(user, validationCode, tokenPass);
        }
        return false;
    }
}
