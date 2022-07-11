package com.fayardev.membershipsystem.controllers;

import com.fayardev.membershipsystem.controllers.abstracts.IProfileController;
import com.fayardev.membershipsystem.services.ProfileService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController extends BaseController implements IProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    @GetMapping("/my-profile")
    public Object getMyProfile(HttpServletRequest request) throws Exception {
        return null;
    }

    @Override
    @PostMapping("/change-about-me")
    public boolean changeAboutMe(HttpServletRequest request, @RequestBody Map<String, Object> map) throws Exception {
        return false;
    }

    @Override
    @PostMapping("/update-avatar")
    public boolean updateAvatar(HttpServletRequest request, @RequestBody Map<String, Object> map) throws Exception {
        return false;
    }

    @Override
    @PostMapping("/delete-avatar")
    public boolean deleteAvatar(HttpServletRequest request) throws Exception {
        return false;
    }

    @Override
    @GetMapping("/{username}")
    public Object getProfile(HttpServletRequest request, @PathVariable String username) throws JSONException, ParseException {
        return null;
    }

    @Override
    @PostMapping("/detail")
    public Object userMiniDetail(HttpServletRequest request, @RequestBody Map<String, Object> map) throws JSONException {
        return null;
    }

    @Override
    @PostMapping("/timeline-get-profile")
    public Object timelineGetProfile(HttpServletRequest request, @RequestBody Map<String, Object> map) throws Exception {
        return null;
    }

    @Override
    @PostMapping("/timeline")
    public Object timeline(HttpServletRequest request, @RequestBody Map<String, Object> map) throws Exception {
        return null;
    }

    @Override
    @PostMapping("/search")
    public Object search(HttpServletRequest request, @RequestBody Map<String, Object> map) throws JSONException {
        return null;
    }


}
