package com.fayardev.regms.controllers;

import com.fayardev.regms.controllers.abstracts.IProfileController;
import com.fayardev.regms.dtos.*;
import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.Profile;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.exceptions.UserException;
import com.fayardev.regms.exceptions.enums.ErrorComponents;
import com.fayardev.regms.exceptions.enums.Errors;
import com.fayardev.regms.services.ProfileService;
import com.fayardev.regms.services.UserService;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "*", maxAge = 3600)
public final class ProfileController extends BaseController implements IProfileController {

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
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> addProfile(Authentication authentication, @RequestBody ProfileDto profileDto) throws Exception {
        var user = userService.getEntityByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Profile profile = modelMapper.map(profileDto, Profile.class);
        profile.setUser((User) user);
        return ResponseEntity.ok(profileService.saveEntity(profile));
    }

    @Override
    @GetMapping("/my-profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ProfileDto> getMyProfile(Authentication authentication) throws Exception {
        var user = userService.getEntityByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modelMapper.map(profileService.getEntityByUser((User) user), ProfileDto.class));
    }

    @Override
    @PostMapping("/change-about-me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> changeAboutMe(Authentication authentication, @RequestBody String aboutMe) throws Exception {
        var user = userService.getEntityByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        var profile = profileService.getEntityByUser((User) user);
        ((Profile) profile).setAboutMe(aboutMe);

        return ResponseEntity.ok(profileService.changeAboutMe((Profile) profile));
    }

    @Override
    @PostMapping("/update-avatar")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> updateAvatar(Authentication authentication, @RequestBody String base64) throws Exception {
        var user = userService.getEntityByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        var profile = profileService.getEntityByUser((User) user);
        ((Profile) profile).setAvatarPath(base64);

        return ResponseEntity.ok(profileService.updateAvatar((Profile) profile));
    }

    @Override
    @PostMapping("/delete-avatar")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Boolean> deleteAvatar(Authentication authentication) throws Exception {
        var user = userService.getEntityByUsername(authentication.getName());
        if (user == null) {
            throw new UserException("User Null", Errors.NULL, ErrorComponents.USER);
        }
        return ResponseEntity.ok(profileService.deleteAvatar((Profile) profileService.getEntityByUser((User) user)));
    }

    @Override
    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> getProfile(Authentication authentication, @PathVariable String username, @RequestParam String type) throws JSONException, UserException {
        var user = userService.getEntityByUsername(authentication.getName());
        if (user == null) {
            throw new UserException("User Null", Errors.NULL, ErrorComponents.USER);
        }
        BaseEntity theUser = userService.getEntityByUsername(username);
        if (theUser == null) {
            return ResponseEntity.notFound().build();
        }
        BaseEntity theProfile = profileService.getEntityByUser((User) theUser);
        if (theProfile == null) {
            return ResponseEntity.notFound().build();
        }
        if (type.equals("mini")) {
            OtherUserMiniDto otherUserMiniDto = modelMapper.map(theUser, OtherUserMiniDto.class);
            OtherProfileMiniDto otherProfileMiniDto = modelMapper.map(theProfile, OtherProfileMiniDto.class);
            otherProfileMiniDto.setOtherUserMiniDto(otherUserMiniDto);
            return ResponseEntity.ok(otherProfileMiniDto);
        }
        OtherUserDto otherUserDto = modelMapper.map(theUser, OtherUserDto.class);
        OtherProfileDto otherProfileDto = modelMapper.map(theProfile, OtherProfileDto.class);
        otherProfileDto.setOtherUserDto(otherUserDto);

        return ResponseEntity.ok(otherProfileDto);
    }

    @Override
    @PostMapping("/timeline-get-profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> timelineGetProfile(Authentication authentication, @RequestBody Map<String, Object> map) throws Exception {
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/timeline")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> timeline(Authentication authentication, @RequestBody Map<String, Object> map) throws Exception {
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/search")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> search(Authentication authentication, @RequestBody Map<String, Object> map) throws JSONException {
        return ResponseEntity.ok().build();
    }
}
