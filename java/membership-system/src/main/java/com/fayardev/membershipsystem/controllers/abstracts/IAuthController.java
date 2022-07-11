package com.fayardev.membershipsystem.controllers.abstracts;

import com.fayardev.membershipsystem.dtos.UserDto;
import org.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IAuthController {
    @PostMapping("/sign-up")
    boolean signUp(@RequestBody UserDto userDto) throws Exception;

    @PostMapping("/check-valid-password")
    boolean checkIfValidOldPassword(HttpServletRequest request, @RequestBody Map<String, String> passwordMap) throws JSONException;

    @PostMapping("/reset-password")
    boolean resetPassword(@RequestBody Map<String, String> userEmail) throws Exception;

    @PostMapping("/change-password")
    boolean showChangePasswordPage(@RequestBody Map<String, String> token) throws Exception;

    @PostMapping("/save-password-forgot")
    String savePassword(@RequestBody Map<String, String> passwordMap) throws Exception;

    @PostMapping("/change-hash-password")
    boolean changePassword(HttpServletRequest request, @RequestBody Map<String, String> passwordMap) throws Exception;
}
