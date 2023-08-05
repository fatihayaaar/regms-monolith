package com.fayardev.regms.controllers.abstracts;

import com.fayardev.regms.dtos.PasswordDto;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IPasswordController {

    ResponseEntity<Boolean> checkIfValidOldPassword(Authentication authentication, @RequestBody PasswordDto passwordDto) throws JSONException;

    ResponseEntity<Boolean> forgotPassword(@RequestBody PasswordDto passwordDto) throws Exception;

    ResponseEntity<Boolean> changePasswordWithToken(@RequestBody PasswordDto passwordDto) throws Exception;

    ResponseEntity<String> getPasswordForgotToken(@RequestBody PasswordDto passwordDto) throws Exception;

    ResponseEntity<Boolean> changePassword(Authentication authentication, @RequestBody PasswordDto passwordDto) throws Exception;
}
