package com.fayardev.regms.util;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.PasswordResetToken;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.services.UserService;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class EmailToken {
    private static EmailToken emailToken;

    private EmailToken() {

    }

    public static EmailToken getInstance() {
        if (emailToken == null) {
            emailToken = new EmailToken();
        }
        return emailToken;
    }

    public void sendForgotPasswordValidationCode(MailSender mailSender,
                                                 UserService userService,
                                                 User user) throws Exception {
        BaseEntity passwordResetToken = userService.getPassTokenByEmail(((User) user).getEmailAddress());

        String tokenPass = UUID.randomUUID().toString();
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        String validationCode = String.format("%06d", number);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("password@example.com");
        message.setTo(((User) user).getEmailAddress());
        message.setSubject("Forgot Password Token");
        message.setText(validationCode);

        if (passwordResetToken.getID() != -1) {
            PasswordResetToken passwordResetToken1 = (PasswordResetToken) passwordResetToken;
            if (!passwordResetToken1.isActive() || (new Date().getTime() - passwordResetToken1.getExpiryDate().getTime()) / 1000 > 120) {
                mailSender.send(message);
                userService.createPasswordResetTokenForUser((User) user, validationCode, tokenPass);
            }
        } else {
            mailSender.send(message);
            userService.createPasswordResetTokenForUser((User) user, validationCode, tokenPass);
        }
    }
}
