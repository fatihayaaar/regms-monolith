package com.fayardev.regms.util.temptoken.abstracts;

import com.fayardev.regms.entities.User;
import com.fayardev.regms.services.PasswordResetService;
import org.springframework.mail.MailSender;

public interface IValidationToken {

    boolean sendForgotPasswordValidationCode(MailSender mailSender, PasswordResetService service, User user) throws Exception;
}
