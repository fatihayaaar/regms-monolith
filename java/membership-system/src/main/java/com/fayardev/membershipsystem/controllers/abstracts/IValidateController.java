package com.fayardev.membershipsystem.controllers.abstracts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IValidateController {

    @GetMapping("/validate/username/{username}")
    boolean isThereUsername(@PathVariable String username);

    @GetMapping("/validate/email-address/{email}")
    boolean isThereEmail(@PathVariable String email);

    @GetMapping("/validate/phoneNo/{phoneNo}")
    boolean isTherePhoneNo(@PathVariable String phoneNo);
}
