package com.fayardev.regms.controllers.abstracts;

import com.fayardev.regms.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface IUserController {

    ResponseEntity<Boolean> change(Authentication authentication, @RequestParam String type, @RequestParam String username) throws Exception;

    ResponseEntity<Boolean> freeze(Authentication authentication) throws Exception;

    ResponseEntity<Boolean> update(Authentication authentication, @RequestBody User user) throws Exception;

    ResponseEntity<Boolean> delete(Authentication authentication, @RequestBody Long id) throws Exception;
}
