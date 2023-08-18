package com.fayardev.regms.controllers.abstracts;

import com.fayardev.regms.dtos.RefreshTokenDto;
import com.fayardev.regms.dtos.AuthUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IAuthController {

    ResponseEntity<?> signUp(@RequestBody @Valid AuthUserDto userDto) throws Exception;

    ResponseEntity<?> refreshToken(@RequestBody @Valid RefreshTokenDto refreshToken);
}
