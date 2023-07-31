package com.fayardev.regms.controllers;

import com.fayardev.regms.controllers.abstracts.IValidateController;
import com.fayardev.regms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validate")
@CrossOrigin(origins = "*", maxAge = 3600)
public final class ValidateController extends BaseController implements IValidateController {

    private final UserService userService;

    @Autowired
    public ValidateController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/username/{username}")
    public ResponseEntity<Boolean> isThereUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getEntityByUsername(username).getID() != -1);
    }

    @Override
    @GetMapping("/email-address/{email}")
    public ResponseEntity<Boolean> isThereEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getEntityByEmail(email).getID() != -1);
    }

    @Override
    @GetMapping("/phoneNo/{phoneNo}")
    public ResponseEntity<Boolean> isTherePhoneNo(@PathVariable String phoneNo) {
        return ResponseEntity.ok(userService.getEntityByPhoneNo(phoneNo).getID() != -1);
    }

}
