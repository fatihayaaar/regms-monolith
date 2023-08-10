package com.fayardev.regms.controllers;

import com.fayardev.regms.auth.RefreshToken;
import com.fayardev.regms.controllers.abstracts.IAuthController;
import com.fayardev.regms.dtos.AuthUserDto;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.services.UserService;
import com.fayardev.regms.validates.UserValidate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<?> signUp(@RequestBody @Valid AuthUserDto userDto) throws Exception {
        if (!UserValidate.passwordLengthValidate(userDto.getPassword())) {
            if (!UserValidate.passwordValidate(userDto.getPassword())) {
                return ResponseEntity.ok(false);
            }
        }
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        return ResponseEntity.ok(userService.saveEntity(modelMapper.map(userDto, User.class)));
    }

    @Override
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody @Valid RefreshToken refreshToken) {
        return ResponseEntity.ok().build();
    }
}
