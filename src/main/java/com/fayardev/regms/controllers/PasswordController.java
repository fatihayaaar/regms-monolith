package com.fayardev.regms.controllers;

import com.fayardev.regms.controllers.abstracts.IPasswordController;
import com.fayardev.regms.dtos.PasswordDto;
import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.exceptions.UserException;
import com.fayardev.regms.exceptions.enums.ErrorComponents;
import com.fayardev.regms.exceptions.enums.Errors;
import com.fayardev.regms.services.UserService;
import com.fayardev.regms.util.HeaderUtil;
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
    public boolean resetPassword(@RequestBody PasswordDto passwordDto) throws Exception {
        var user = userService.getEntityByEmail(passwordDto.getEmailAddress());
        if (user == null) {
            throw new UserException("User Null", Errors.NULL, ErrorComponents.USER);
        }

        return false;
    }

    @Override
    @PostMapping("/change-password")
    public boolean showChangePasswordPage(@RequestBody PasswordDto passwordDto) throws Exception {
        return false;
    }

    @Override
    @PostMapping("/save-password-forgot")
    public String savePassword(@RequestBody PasswordDto passwordDto) throws Exception {
        return "";
    }

    @Override
    @PostMapping("/change-hash-password")
    public boolean changePassword(HttpServletRequest request, @RequestBody PasswordDto passwordDto) throws Exception {
        return false;
    }
}
