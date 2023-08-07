package com.fayardev.regms.controllers.abstracts;

import com.fayardev.regms.dtos.AuthUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IAuthController {

    ResponseEntity<Object> signUp(@Valid @RequestBody AuthUserDto userDto) throws Exception;
}
