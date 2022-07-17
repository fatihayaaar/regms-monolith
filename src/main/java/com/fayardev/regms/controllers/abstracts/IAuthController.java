package com.fayardev.regms.controllers.abstracts;

import com.fayardev.regms.dtos.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthController {

    @PostMapping("/sign-up")
    boolean signUp(@RequestBody UserDto userDto) throws Exception;

}
