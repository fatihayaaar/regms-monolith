package com.fayardev.membershipsystem.controllers;

import com.fayardev.membershipsystem.controllers.abstracts.IAuthController;
import com.fayardev.membershipsystem.dtos.UserDto;
import com.fayardev.membershipsystem.entities.BaseEntity;
import com.fayardev.membershipsystem.entities.User;
import com.fayardev.membershipsystem.services.UserService;
import com.fayardev.membershipsystem.util.HeaderUtil;
import com.fayardev.membershipsystem.validates.UserValidate;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public final class AuthController extends BaseController implements IAuthController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    @PostMapping("/sign-up")
    public boolean signUp(@RequestBody UserDto userDto) throws Exception {
        if (!UserValidate.passwordLengthValidate(userDto.getHashPassword())) {
            if (!UserValidate.passwordValidate(userDto.getHashPassword())) {
                return false;
            }
        }
        userDto.setHashPassword(bCryptPasswordEncoder.encode(userDto.getHashPassword()));
        return userService.add(modelMapper.map(userDto, User.class));
    }

}
