package com.fayardev.regms.dtos;

import com.fayardev.regms.entities.User;

public class ProfileDto {

    private String nameAndSurname;
    private String aboutMe;
    private String avatarPath;
    private boolean privacy;
    private boolean showProfile;
    private boolean isOpenNotification;
    private User user;

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public boolean isShowProfile() {
        return showProfile;
    }

    public void setShowProfile(boolean showProfile) {
        this.showProfile = showProfile;
    }

    public boolean isOpenNotification() {
        return isOpenNotification;
    }

    public void setOpenNotification(boolean openNotification) {
        isOpenNotification = openNotification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
