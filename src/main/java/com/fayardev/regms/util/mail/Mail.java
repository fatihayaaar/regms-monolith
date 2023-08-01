package com.fayardev.regms.util.mail;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class Mail {

    public static boolean sendPasswordResetValidationCode(MailSender mailSender, String validationCode, String emailAddress) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("password@example.com");
        message.setTo(emailAddress);
        message.setSubject("Forgot Password Token");
        message.setText(validationCode);

        mailSender.send(message);
        return true;
    }
}
