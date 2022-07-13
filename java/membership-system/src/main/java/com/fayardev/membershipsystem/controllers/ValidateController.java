package com.fayardev.membershipsystem.controllers;

import com.fayardev.membershipsystem.controllers.abstracts.IValidateController;
import com.fayardev.membershipsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate")
public final class ValidateController extends BaseController implements IValidateController {

    private final UserService userService;

    @Autowired
    public ValidateController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/username/{username}")
    public boolean isThereUsername(@PathVariable String username) {
        return userService.getEntityByUsername(username).getID() != -1;
    }

    @Override
    @GetMapping("/email-address/{email}")
    public boolean isThereEmail(@PathVariable String email) {
        return userService.getEntityByEmail(email).getID() != -1;
    }

    @Override
    @GetMapping("/phoneNo/{phoneNo}")
    public boolean isTherePhoneNo(@PathVariable String phoneNo) {
        return userService.getEntityByPhoneNo(phoneNo).getID() != -1;
    }

}
