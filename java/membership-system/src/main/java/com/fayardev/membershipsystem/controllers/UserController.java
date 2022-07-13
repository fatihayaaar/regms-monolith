package com.fayardev.membershipsystem.controllers;

import com.fayardev.membershipsystem.controllers.abstracts.IUserController;
import com.fayardev.membershipsystem.entities.User;
import com.fayardev.membershipsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public final class UserController extends BaseController implements IUserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping("/change-username")
    public boolean changeUsername(HttpServletRequest request, @RequestBody Map<String, String> passwordMap) throws Exception {
        return false;
    }

    @Override
    @PostMapping("/update")
    public boolean update(@RequestBody User user) throws Exception {
        return userService.update(user);
    }

    @Override
    @PostMapping("/delete")
    public boolean delete(@RequestBody int id) throws Exception {
        return userService.delete(id);
    }
}
