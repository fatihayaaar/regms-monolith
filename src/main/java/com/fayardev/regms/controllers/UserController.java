package com.fayardev.regms.controllers;

import com.fayardev.regms.controllers.abstracts.IUserController;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        User user = (User) userService.getEntityByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.ok(false);
        }
        if (type == null) {
            return ResponseEntity.ok(false);
        }
        if (val == null) {
            return ResponseEntity.ok(false);
        }
        switch (type) {
            case "username" -> {
                user.setUsername(val);
                return ResponseEntity.ok(userService.changeUsername(user));
            }
            case "emailAddress" -> {
                user.setEmailAddress(val);
                return ResponseEntity.ok(userService.changeEmailAddress(user));
            }
            case "phoneNo" -> {
                user.setPhoneNo(val);
                return ResponseEntity.ok(userService.changePhoneNo(user));
            }
        }

        return ResponseEntity.ok(false);
    }

    @Override
    @PostMapping("/freeze")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> freeze(Authentication authentication) throws Exception {
        var user = userService.getEntityByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(userService.freeze((User) user));
    }

    @Override
    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> update(Authentication authentication, @RequestBody @Valid User user) throws Exception {
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> delete(Authentication authentication, @RequestBody Long id) throws Exception {
        return ResponseEntity.ok(userService.delete(id));
    }
}
