package com.fayardev.membershipsystem.controllers.abstracts;

import com.fayardev.membershipsystem.dtos.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthController {

    @PostMapping("/sign-up")
    boolean signUp(@RequestBody UserDto userDto) throws Exception;

}
