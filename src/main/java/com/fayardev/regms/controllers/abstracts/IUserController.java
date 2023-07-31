package com.fayardev.regms.controllers.abstracts;

import com.fayardev.regms.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public interface IUserController {

    @PostMapping("/change")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<Boolean> change(Authentication authentication, @RequestParam String type, @RequestParam String username) throws Exception;

    @PostMapping("/freeze")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<Boolean> freeze(Authentication authentication) throws Exception;

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<Boolean> update(Authentication authentication, @RequestBody User user) throws Exception;

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Boolean> delete(Authentication authentication, @RequestBody Long id) throws Exception;
}
