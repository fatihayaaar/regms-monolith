package com.fayardev.regms.controllers;

import com.fayardev.regms.controllers.abstracts.IUserController;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.services.UserService;
import com.fayardev.regms.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> change(Authentication authentication, @RequestParam String type, @RequestParam String val) throws Exception {
        var user = userService.getEntityByUsername(authentication.getName());
        if (user.getID() == -1) {
            return ResponseEntity.ok(false);
        }
        if (type == null) {
            return ResponseEntity.ok(false);
        }
        if (val == null) {
            return ResponseEntity.ok(false);
        }

        User userEntity = (User) user;
        switch (type) {
            case "username" -> {
                userEntity.setUsername(val);
                return ResponseEntity.ok(userService.changeUsername(userEntity));
            }
            case "emailAddress" -> {
                userEntity.setEmailAddress(val);
                return ResponseEntity.ok(userService.changeEmailAddress(userEntity));
            }
            case "phoneNo" -> {
                userEntity.setPhoneNo(val);
                return ResponseEntity.ok(userService.changePhoneNo(userEntity));
            }
        }

        return ResponseEntity.ok(false);
    }

    @Override
    @PostMapping("/freeze")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> freeze(Authentication authentication) throws Exception {
        var user = userService.getEntityByUsername(authentication.getName());
        if (user.getID() == -1) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(userService.freeze((User) user));
    }

    @Override
    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> update(Authentication authentication, @RequestBody User user) throws Exception {
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> delete(Authentication authentication, @RequestBody int id) throws Exception {
        return ResponseEntity.ok(userService.delete(id));
    }
}
