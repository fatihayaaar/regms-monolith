package com.fayardev.membershipsystem.controllers;

import com.fayardev.membershipsystem.controllers.abstracts.IAuthController;
import com.fayardev.membershipsystem.entities.BaseEntity;
import com.fayardev.membershipsystem.entities.User;
import com.fayardev.membershipsystem.services.UserService;
import com.fayardev.membershipsystem.util.HeaderUtil;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController implements IAuthController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @PostMapping("/sign-up")
    public boolean signUp(@RequestBody Map<String, Object> map) throws Exception {
        return false;
    }

    @Override
    @PostMapping("/check-valid-password")
    public boolean checkIfValidOldPassword(HttpServletRequest request, @RequestBody Map<String, String> passwordMap) throws JSONException {
        BaseEntity user = userService.getEntityById(Integer.parseInt(HeaderUtil.getTokenPayloadID(request)));
        return bCryptPasswordEncoder.matches(passwordMap.get("oldPassword"), ((User) user).getHashPassword());
    }

    @Override
    @PostMapping("/reset-password")
    public boolean resetPassword(@RequestBody Map<String, String> userEmail) throws Exception {
        return false;
    }

    @Override
    @PostMapping("/change-password")
    public boolean showChangePasswordPage(@RequestBody Map<String, String> token) throws Exception {
        return false;
    }

    @Override
    @PostMapping("/save-password-forgot")
    public String savePassword(@RequestBody Map<String, String> passwordMap) throws Exception {
        return "";
    }

    @Override
    @PostMapping("/change-hash-password")
    public boolean changePassword(HttpServletRequest request, @RequestBody Map<String, String> passwordMap) throws Exception {
        return false;
    }

}
