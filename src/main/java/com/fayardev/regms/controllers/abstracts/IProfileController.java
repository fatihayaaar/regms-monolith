package com.fayardev.regms.controllers.abstracts;

import com.fayardev.regms.dtos.ProfileDto;
import com.fayardev.regms.exceptions.UserException;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

public interface IProfileController {

    @GetMapping("/add")
    Object addProfile(HttpServletRequest request, @RequestBody ProfileDto profileDto) throws Exception;

    @GetMapping("/my-profile")
    Object getMyProfile(HttpServletRequest request) throws Exception;

    @PostMapping("/change-about-me")
    boolean changeAboutMe(HttpServletRequest request, @RequestBody String aboutMe) throws Exception;

    @PostMapping("/update-avatar")
    boolean updateAvatar(HttpServletRequest request, @RequestBody String base64) throws Exception;

    @PostMapping("/delete-avatar")
    boolean deleteAvatar(HttpServletRequest request) throws Exception;

    @GetMapping("/{username}")
    Object getProfile(HttpServletRequest request, @PathVariable String username, @RequestParam String type) throws JSONException, ParseException, UserException;

    @PostMapping("/timeline-get-profile")
    Object timelineGetProfile(HttpServletRequest request, @RequestBody Map<String, Object> map) throws Exception;

    @PostMapping("/timeline")
    Object timeline(HttpServletRequest request, @RequestBody Map<String, Object> map) throws Exception;

    @PostMapping("/search")
    Object search(HttpServletRequest request, @RequestBody Map<String, Object> map) throws JSONException;
}
