package com.fayardev.regms.controllers;

import com.fayardev.regms.auth.util.JWTUtil;
import com.fayardev.regms.controllers.abstracts.IAuthController;
import com.fayardev.regms.dtos.AuthUserDto;
import com.fayardev.regms.dtos.RefreshTokenDto;
import com.fayardev.regms.entities.RefreshToken;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.services.RefreshTokenService;
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
    private final JWTUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, JWTUtil jwtUtil, RefreshTokenService refreshTokenService, ModelMapper modelMapper) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
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
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody @Valid RefreshTokenDto refreshToken) {
        return refreshTokenService.findByToken(refreshToken.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtUtil.generateTokenByUsername(user.getUsername());
                    return ResponseEntity.ok(RefreshTokenDto.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenService.createRefreshToken(user.getUsername()).getToken())
                            .build());
                }).orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
    }
}
