package com.fayardev.regms.controllers.abstracts;

import com.fayardev.regms.dtos.ProfileDto;
import com.fayardev.regms.exceptions.UserException;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

public interface IProfileController {

    Object addProfile(Authentication authentication, @RequestBody ProfileDto profileDto) throws Exception;

    Object getMyProfile(Authentication authentication) throws Exception;

    ResponseEntity<Boolean> changeAboutMe(Authentication authentication, @RequestBody String aboutMe) throws Exception;

    ResponseEntity<Boolean> updateAvatar(Authentication authentication, @RequestBody String base64) throws Exception;

    ResponseEntity<Boolean> deleteAvatar(Authentication authentication) throws Exception;

    ResponseEntity<Object> getProfile(Authentication authentication, @PathVariable String username, @RequestParam String type) throws JSONException, ParseException, UserException;

    ResponseEntity<Object> timelineGetProfile(Authentication authentication, @RequestBody Map<String, Object> map) throws Exception;

    ResponseEntity<Object> timeline(Authentication authentication, @RequestBody Map<String, Object> map) throws Exception;

    ResponseEntity<Object> search(Authentication authentication, @RequestBody Map<String, Object> map) throws JSONException;
}
