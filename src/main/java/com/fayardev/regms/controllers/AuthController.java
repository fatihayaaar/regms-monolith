package com.fayardev.regms.controllers;

import com.fayardev.regms.controllers.abstracts.IAuthController;
import com.fayardev.regms.dtos.UserDto;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.services.UserService;
import com.fayardev.regms.validates.UserValidate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
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
