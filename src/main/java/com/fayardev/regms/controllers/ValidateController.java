package com.fayardev.regms.controllers;

import com.fayardev.regms.controllers.abstracts.IValidateController;
import com.fayardev.regms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validate")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ValidateController extends BaseController implements IValidateController {

    private final UserService userService;

    @Autowired
    public ValidateController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/username/{username}")
    public ResponseEntity<Boolean> isThereUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getEntityByUsername(username) != null);
    }

    @Override
    @GetMapping("/email-address/{email}")
    public ResponseEntity<Boolean> isThereEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getEntityByEmail(email) != null);
    }

    @Override
    @GetMapping("/phoneNo/{phoneNo}")
    public ResponseEntity<Boolean> isTherePhoneNo(@PathVariable String phoneNo) {
        return ResponseEntity.ok(userService.getEntityByPhoneNo(phoneNo) != null);
    }

}
