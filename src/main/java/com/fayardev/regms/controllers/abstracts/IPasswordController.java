package com.fayardev.regms.controllers.abstracts;

import com.fayardev.regms.dtos.PasswordDto;
import org.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface IPasswordController {

    @PostMapping("/check-valid-password")
    boolean checkIfValidOldPassword(HttpServletRequest request, @RequestBody PasswordDto passwordDto) throws JSONException;

    @PostMapping("/reset-password")
    boolean forgotPassword(@RequestBody PasswordDto passwordDto) throws Exception;

    @PostMapping("/change-password")
    boolean changePasswordWithToken(@RequestBody PasswordDto passwordDto) throws Exception;

    @PostMapping("/save-password-forgot")
    String getPasswordForgotToken(@RequestBody PasswordDto passwordDto) throws Exception;

    @PostMapping("/change-hash-password")
    boolean changePassword(HttpServletRequest request, @RequestBody PasswordDto passwordDto) throws Exception;
}
