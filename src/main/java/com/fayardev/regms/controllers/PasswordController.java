package com.fayardev.regms.controllers;

import com.fayardev.regms.controllers.abstracts.IPasswordController;
import com.fayardev.regms.dtos.PasswordDto;
import com.fayardev.regms.entities.PasswordReset;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.exceptions.UserException;
import com.fayardev.regms.exceptions.enums.ErrorComponents;
import com.fayardev.regms.exceptions.enums.Errors;
import com.fayardev.regms.services.PasswordResetService;
import com.fayardev.regms.services.UserService;
import com.fayardev.regms.util.HeaderUtil;
import com.fayardev.regms.util.temptoken.ValidationToken;
import com.fayardev.regms.validates.UserValidate;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public boolean checkIfValidOldPassword(HttpServletRequest request, @RequestBody PasswordDto passwordDto) throws JSONException {
        var user = userService.getEntityById(Integer.parseInt(HeaderUtil.getTokenPayloadID(request)));
        if (user == null) {
            return false;
        }
        return bCryptPasswordEncoder.matches(passwordDto.getOldPassword(), user.getHashPassword());
    }

    @Override
    @PostMapping("/forgot-password")
    public boolean forgotPassword(@RequestBody PasswordDto passwordDto) throws Exception {
        var user = userService.getEntityByEmail(passwordDto.getEmailAddress());
        if (user == null) {
            throw new UserException("User Null", Errors.NULL, ErrorComponents.USER);
        }
        return ValidationToken.getInstance().sendForgotPasswordValidationCode(mailSender, service, (User) user);
    }

    @Override
    @PostMapping("/change-password-with-token")
    public boolean changePasswordWithToken(@RequestBody PasswordDto passwordDto) throws Exception {
        if (!UserValidate.passwordLengthValidate(passwordDto.getNewPassword())) {
            if (!UserValidate.passwordValidate(passwordDto.getNewPassword())) {
                return false;
            }
        }
        var password = passwordDto.getNewPassword();
        var token = passwordDto.getToken();
        if (token == null) {
            return false;
        }
        var user = service.getUserByTokenPassword(passwordDto.getToken());
        if (user.getID() == -1) {
            throw new UserException("User Null", Errors.NULL, ErrorComponents.USER);
        }
        return service.changeUserPassword((User) user, password, token);
    }

    @Override
    @PostMapping("/get-password-forgot-token")
    public String getPasswordForgotToken(@RequestBody PasswordDto passwordDto) throws Exception {
        var user = userService.getEntityByEmail(passwordDto.getEmailAddress());
        if (user == null) {
            throw new UserException("User Null", Errors.NULL, ErrorComponents.USER);
        }
        PasswordReset passwordReset = (PasswordReset) service.getPassTokenByEmail(passwordDto.getEmailAddress());
        service.numberOfInteractionsInc(passwordReset);

        String result = service.validatePasswordValidateCode(passwordDto.getValidateCode(), ((User) user).getEmailAddress());
        if (result == null) {
            return "expired";
        }

        //passwordReset = (PasswordReset) service.getUserByTokenPassword(passwordDto.getToken());
        if (passwordReset.isActive()) {
            service.activeCompPassToken(passwordReset);
            return passwordReset.getTokenPassword();
        }
        return "expired";
    }

    @Override
    @PostMapping("/change-password")
    public boolean changePassword(HttpServletRequest request, @RequestBody PasswordDto passwordDto) throws Exception {
        if (!checkIfValidOldPassword(request, passwordDto)) {
            return false;
        }
        var user = userService.getEntityById(Integer.parseInt(HeaderUtil.getTokenPayloadID(request)));
        if (user == null) {
            return false;
        }
        if (!UserValidate.passwordLengthValidate(passwordDto.getNewPassword())) {
            if (!UserValidate.passwordValidate(passwordDto.getNewPassword())) {
                return false;
            }
        }
        user.setHashPassword(bCryptPasswordEncoder.encode(passwordDto.getNewPassword()));
        return userService.update(user);
    }
}
