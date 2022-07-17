package com.fayardev.membershipsystem.controllers;

import com.fayardev.membershipsystem.controllers.abstracts.IPasswordController;
import com.fayardev.membershipsystem.entities.BaseEntity;
import com.fayardev.membershipsystem.entities.User;
import com.fayardev.membershipsystem.services.UserService;
import com.fayardev.membershipsystem.util.HeaderUtil;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/password")
@CrossOrigin(origins = "*", maxAge = 3600)
public final class PasswordController extends BaseController implements IPasswordController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public PasswordController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
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
