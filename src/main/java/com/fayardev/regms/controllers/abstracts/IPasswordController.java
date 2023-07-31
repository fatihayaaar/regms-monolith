package com.fayardev.regms.controllers.abstracts;

import com.fayardev.regms.dtos.PasswordDto;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface IPasswordController {

    @PostMapping("/check-valid-password")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<Boolean> checkIfValidOldPassword(Authentication authentication, @RequestBody PasswordDto passwordDto) throws JSONException;

    @PostMapping("/reset-password")
    ResponseEntity<Boolean> forgotPassword(@RequestBody PasswordDto passwordDto) throws Exception;

    @PostMapping("/change-password")
    ResponseEntity<Boolean> changePasswordWithToken(@RequestBody PasswordDto passwordDto) throws Exception;

    @PostMapping("/save-password-forgot")
    ResponseEntity<String> getPasswordForgotToken(@RequestBody PasswordDto passwordDto) throws Exception;

    @PostMapping("/change-hash-password")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<Boolean> changePassword(Authentication authentication, @RequestBody PasswordDto passwordDto) throws Exception;
}
