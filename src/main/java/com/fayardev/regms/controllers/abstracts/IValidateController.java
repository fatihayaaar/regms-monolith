package com.fayardev.regms.controllers.abstracts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IValidateController {

    ResponseEntity<Boolean> isThereUsername(@PathVariable String username);

    ResponseEntity<Boolean> isThereEmail(@PathVariable String email);

    ResponseEntity<Boolean> isTherePhoneNo(@PathVariable String phoneNo);
}
