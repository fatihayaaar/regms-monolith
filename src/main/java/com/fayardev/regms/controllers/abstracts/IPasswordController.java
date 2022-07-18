package com.fayardev.regms.controllers.abstracts;

import com.fayardev.regms.dtos.PasswordDto;
import org.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IPasswordController {

    @PostMapping("/check-valid-password")
    boolean checkIfValidOldPassword(HttpServletRequest request, @RequestBody Map<String, String> passwordMap) throws JSONException;

    @PostMapping("/reset-password")
    boolean resetPassword(@RequestBody PasswordDto passwordDto) throws Exception;

    @PostMapping("/change-password")
    boolean showChangePasswordPage(@RequestBody PasswordDto passwordDto) throws Exception;

    @PostMapping("/save-password-forgot")
    String savePassword(@RequestBody PasswordDto passwordDto) throws Exception;

    @PostMapping("/change-hash-password")
    boolean changePassword(HttpServletRequest request, @RequestBody PasswordDto passwordDto) throws Exception;
}
