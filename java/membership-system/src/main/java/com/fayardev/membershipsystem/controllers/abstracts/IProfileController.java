package com.fayardev.membershipsystem.controllers.abstracts;

import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

public interface IProfileController {
    @GetMapping("/my-profile")
    Object getMyProfile(HttpServletRequest request) throws Exception;

    @PostMapping("/change-about-me")
    boolean changeAboutMe(HttpServletRequest request, @RequestBody Map<String, Object> map) throws Exception;

    @PostMapping("/update-avatar")
    boolean updateAvatar(HttpServletRequest request, @RequestBody Map<String, Object> map) throws Exception;

    @PostMapping("/delete-avatar")
    boolean deleteAvatar(HttpServletRequest request) throws Exception;

    @GetMapping("/{username}")
    Object getProfile(HttpServletRequest request, @PathVariable String username) throws JSONException, ParseException;

    @PostMapping("/detail")
    Object userMiniDetail(HttpServletRequest request, @RequestBody Map<String, Object> map) throws JSONException;

    @PostMapping("/timeline-get-profile")
    Object timelineGetProfile(HttpServletRequest request, @RequestBody Map<String, Object> map) throws Exception;

    @PostMapping("/timeline")
    Object timeline(HttpServletRequest request, @RequestBody Map<String, Object> map) throws Exception;

    @PostMapping("/search")
    Object search(HttpServletRequest request, @RequestBody Map<String, Object> map) throws JSONException;
}
