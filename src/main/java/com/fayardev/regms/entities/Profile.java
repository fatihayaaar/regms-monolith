package com.fayardev.regms.entities;

import jakarta.persistence.*;

@Entity
public class Profile extends BaseEntity {

    public static final int MAX_ABOUT_ME_LENGTH = 300;
    public static final int NAME_AND_SURNAME_MAX_LENGTH = 32;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false, length = NAME_AND_SURNAME_MAX_LENGTH)
    private String nameAndSurname;

    private String aboutMe;
    private String avatarPath;

    @Column(nullable = false)
    private boolean privacy;

    @Column(nullable = false)
    private boolean showProfile;

    @Column(nullable = false)
    private boolean isOpenNotification;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(nullable = false)
    private User user;

    public Profile() {
        super();
    }

    @Override
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    @Override
    public String toString() {
        return "Profile {" +
                "'id': " + this.ID + ", " +
                "'username' : '" + this.user.getUsername() + "', " +
                "'nameAndSurname' : '" + this.nameAndSurname + "', " +
                "'aboutMe' : '" + this.aboutMe + "', " +
                "'avatar' : '" + this.avatarPath + "' " +
                "}";
    }
}
