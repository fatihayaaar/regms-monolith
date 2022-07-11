package com.fayardev.membershipsystem.controllers;

import com.fayardev.membershipsystem.entities.User;
import com.fayardev.membershipsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public final class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/change-username")
    public boolean changeUsername(HttpServletRequest request, @RequestBody Map<String, String> passwordMap) throws Exception {
        return false;
    }

    @GetMapping("/validate/username/{username}")
    public boolean isThereUsername(@PathVariable String username) {
        return userService.getEntityByUsername(username).getID() != -1;
    }

    @GetMapping("/validate/email/{email}")
    public boolean isThereEmail(@PathVariable String email) {
        return userService.getEntityByEmail(email).getID() != -1;
    }

    @GetMapping("/validate/phoneNo/{phoneNo}")
    public boolean isTherePhoneNo(@PathVariable String phoneNo) {
        return userService.getEntityByPhoneNo(phoneNo).getID() != -1;
    }

    @PostMapping("/update")
    public boolean update(@RequestBody User user) throws Exception {
        return userService.update(user);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestBody int id) throws Exception {
        return userService.delete(id);
    }
}
