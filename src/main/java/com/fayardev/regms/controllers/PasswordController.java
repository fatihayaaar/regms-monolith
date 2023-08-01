package com.fayardev.regms.controllers;

import com.fayardev.regms.controllers.abstracts.IPasswordController;
import com.fayardev.regms.dtos.PasswordDto;
import com.fayardev.regms.entities.PasswordReset;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.services.PasswordResetService;
import com.fayardev.regms.services.UserService;
import com.fayardev.regms.util.temptoken.ValidationToken;
import com.fayardev.regms.validates.UserValidate;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
@CrossOrigin(origins = "*", maxAge = 3600)
public final class PasswordController extends BaseController implements IPasswordController {

    private final PasswordResetService service;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender mailSender;
    private final ModelMapper modelMapper;

    @Autowired
    public PasswordController(PasswordResetService service, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, JavaMailSender mailSender, ModelMapper modelMapper) {
        this.service = service;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
        this.mailSender = mailSender;
    }

    @Override
    @PostMapping("/check-valid-password")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> checkIfValidOldPassword(Authentication authentication, @RequestBody PasswordDto passwordDto) throws JSONException {
        var user = userService.getEntityByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(bCryptPasswordEncoder.matches(passwordDto.getOldPassword(), ((User) user).getPassword()));
    }

    @Override
    @PostMapping("/forgot-password")
    public ResponseEntity<Boolean> forgotPassword(@RequestBody PasswordDto passwordDto) throws Exception {
        var user = userService.getEntityByEmail(passwordDto.getEmailAddress());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ValidationToken.getInstance().sendForgotPasswordValidationCode(mailSender, service, (User) user));
    }

    @Override
    @PostMapping("/change-password-with-token")
    public ResponseEntity<Boolean> changePasswordWithToken(@RequestBody PasswordDto passwordDto) throws Exception {
        if (!UserValidate.passwordLengthValidate(passwordDto.getNewPassword())) {
            if (!UserValidate.passwordValidate(passwordDto.getNewPassword())) {
                return ResponseEntity.ok(false);
            }
        }
        var password = passwordDto.getNewPassword();
        var token = passwordDto.getToken();
        if (token == null) {
            return ResponseEntity.ok(false);
        }
        var user = service.getUserByTokenPassword(passwordDto.getToken());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.changeUserPassword((User) user, password, token));
    }

    @Override
    @PostMapping("/get-password-forgot-token")
    public ResponseEntity<String> getPasswordForgotToken(@RequestBody PasswordDto passwordDto) throws Exception {
        var user = userService.getEntityByEmail(passwordDto.getEmailAddress());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        PasswordReset passwordReset = (PasswordReset) service.getPassTokenByEmail(passwordDto.getEmailAddress());
        service.numberOfInteractionsInc(passwordReset);

        String result = service.validatePasswordValidateCode(passwordDto.getValidateCode(), ((User) user).getEmailAddress());
        if (result == null) {
            return ResponseEntity.ok("expired");
        }

        //passwordReset = (PasswordReset) service.getUserByTokenPassword(passwordDto.getToken());
        if (passwordReset.isActive()) {
            service.activeCompPassToken(passwordReset);
            return ResponseEntity.ok(passwordReset.getTokenPassword());
        }
        return ResponseEntity.ok("expired");
    }

    @Override
    @PostMapping("/change-password")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> changePassword(Authentication authentication, @RequestBody PasswordDto passwordDto) throws Exception {
        var user = userService.getEntityByUsername(authentication.getName());
        if (!checkIfValidOldPassword(authentication, passwordDto).hasBody()) {
            return ResponseEntity.ok(false);
        }
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        if (!UserValidate.passwordLengthValidate(passwordDto.getNewPassword())) {
            if (!UserValidate.passwordValidate(passwordDto.getNewPassword())) {
                return ResponseEntity.ok(false);
            }
        }
        ((User) user).setPassword(bCryptPasswordEncoder.encode(passwordDto.getNewPassword()));
        return ResponseEntity.ok(userService.update((User) user));
    }
}
