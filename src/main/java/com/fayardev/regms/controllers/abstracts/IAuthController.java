package com.fayardev.regms.controllers.abstracts;

import com.fayardev.regms.dtos.AuthUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthController {

    @PostMapping("/sign-up")
    ResponseEntity<Object> signUp(@RequestBody AuthUserDto userDto) throws Exception;

}
