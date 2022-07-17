package com.fayardev.regms.controllers;

import com.fayardev.regms.controllers.abstracts.IUserController;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.services.UserService;
import com.fayardev.regms.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public final class UserController extends BaseController implements IUserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping("/change")
    public boolean change(HttpServletRequest request, @RequestParam String type, @RequestParam String val) throws Exception {
        var user = userService.getEntityById(Integer.parseInt(HeaderUtil.getTokenPayloadID(request)));
        if (user == null) {
            return false;
        }
        if (type == null) {
            return false;
        }
        if (val == null) {
            return false;
        }

        switch (type) {
            case "username" -> {
                user.setUsername(val);
                return userService.changeUsername(user);
            }
            case "emailAddress" -> {
                user.setEmailAddress(val);
                return userService.changeEmailAddress(user);
            }
            case "phoneNo" -> {
                user.setPhoneNo(val);
                return userService.changePhoneNo(user);
            }
        }
        return false;
    }

    @Override
    @PostMapping("/freeze")
    public boolean freeze(HttpServletRequest request) throws Exception {
        var user = userService.getEntityById(Integer.parseInt(HeaderUtil.getTokenPayloadID(request)));
        if (user == null) {
            return false;
        }
        return userService.freeze(user);
    }

    @Override
    @PostMapping("/update")
    public boolean update(@RequestBody User user) throws Exception {
        return false;
    }

    @Override
    @PostMapping("/delete")
    public boolean delete(@RequestBody int id) throws Exception {
        return userService.delete(id);
    }
}
