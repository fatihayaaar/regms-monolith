package com.fayardev.membershipsystem.controllers;

import com.fayardev.membershipsystem.controllers.abstracts.IProfileController;
import com.fayardev.membershipsystem.dtos.ProfileDto;
import com.fayardev.membershipsystem.entities.Profile;
import com.fayardev.membershipsystem.services.ProfileService;
import com.fayardev.membershipsystem.services.UserService;
import com.fayardev.membershipsystem.util.HeaderUtil;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController extends BaseController implements IProfileController {

    private final ProfileService profileService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public ProfileController(ProfileService profileService, ModelMapper modelMapper, UserService userService) {
        this.profileService = profileService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    @GetMapping("/add")
    public Object addProfile(HttpServletRequest request, @RequestBody ProfileDto profileDto) throws Exception {
        var user = userService.getEntityById(Integer.parseInt(HeaderUtil.getTokenPayloadID(request)));
        if (user == null) {
            return null;
        }
        Profile profile = modelMapper.map(profileDto, Profile.class);
        profile.setUser(user);
        return profileService.add(profile);
    }

    @Override
    @GetMapping("/my-profile")
    public ProfileDto getMyProfile(HttpServletRequest request) throws Exception {
        var user = userService.getEntityById(Integer.parseInt(HeaderUtil.getTokenPayloadID(request)));
        if (user == null) {
            return null;
        }
        return modelMapper.map(profileService.getEntityByUser(user), ProfileDto.class);
    }

    @Override
    @PostMapping("/change-about-me")
    public boolean changeAboutMe(HttpServletRequest request, @RequestBody String aboutMe) throws Exception {
        var user = userService.getEntityById(Integer.parseInt(HeaderUtil.getTokenPayloadID(request)));
        if (user == null) {
            return false;
        }
        var profile = profileService.getEntityByUser(user);
        ((Profile) profile).setAboutMe(aboutMe);

        return profileService.changeAboutMe((Profile) profile);
    }

    @Override
    @PostMapping("/update-avatar")
    public boolean updateAvatar(HttpServletRequest request, @RequestBody String base64) throws Exception {
        var user = userService.getEntityById(Integer.parseInt(HeaderUtil.getTokenPayloadID(request)));
        if (user == null) {
            return false;
        }
        var profile = profileService.getEntityByUser(user);
        ((Profile) profile).setAvatarPath(base64);

        return profileService.updateAvatar((Profile) profile);
    }

    @Override
    @PostMapping("/delete-avatar")
    public boolean deleteAvatar(HttpServletRequest request) throws Exception {
        var user = userService.getEntityById(Integer.parseInt(HeaderUtil.getTokenPayloadID(request)));
        if (user == null) {
            return false;
        }
        return profileService.deleteAvatar((Profile) profileService.getEntityByUser(user));
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
