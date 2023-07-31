package com.fayardev.regms.controllers.abstracts;

import com.fayardev.regms.dtos.ProfileDto;
import com.fayardev.regms.exceptions.UserException;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

public interface IProfileController {

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    Object addProfile(Authentication authentication, @RequestBody ProfileDto profileDto) throws Exception;

    @GetMapping("/my-profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    Object getMyProfile(Authentication authentication) throws Exception;

    @PostMapping("/change-about-me")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<Boolean> changeAboutMe(Authentication authentication, @RequestBody String aboutMe) throws Exception;

    @PostMapping("/update-avatar")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<Boolean> updateAvatar(Authentication authentication, @RequestBody String base64) throws Exception;

    @PostMapping("/delete-avatar")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<Boolean> deleteAvatar(Authentication authentication) throws Exception;

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<Object> getProfile(Authentication authentication, @PathVariable String username, @RequestParam String type) throws JSONException, ParseException, UserException;

    @PostMapping("/timeline-get-profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<Object> timelineGetProfile(Authentication authentication, @RequestBody Map<String, Object> map) throws Exception;

    @PostMapping("/timeline")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<Object> timeline(Authentication authentication, @RequestBody Map<String, Object> map) throws Exception;

    @PostMapping("/search")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<Object> search(Authentication authentication, @RequestBody Map<String, Object> map) throws JSONException;
}
