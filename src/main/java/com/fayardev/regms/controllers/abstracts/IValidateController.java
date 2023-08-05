package com.fayardev.regms.controllers.abstracts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IValidateController {

    @GetMapping("/username/{username}")
    ResponseEntity<Boolean> isThereUsername(@PathVariable String username);

    @GetMapping("/email-address/{email}")
    ResponseEntity<Boolean> isThereEmail(@PathVariable String email);

    @GetMapping("/phoneNo/{phoneNo}")
    ResponseEntity<Boolean> isTherePhoneNo(@PathVariable String phoneNo);
}
