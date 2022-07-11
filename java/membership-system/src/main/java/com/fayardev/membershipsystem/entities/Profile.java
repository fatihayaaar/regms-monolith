package com.fayardev.membershipsystem.entities;

import javax.persistence.*;

@Entity
@Table(name = "profile")
public class Profile extends BaseEntity {

    public static final int MAX_ABOUT_ME_LENGTH = 300;
    public static final int NAME_AND_SURNAME_MAX_LENGTH = 32;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int ID;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column(name = "nameandsurname", nullable = false, length = NAME_AND_SURNAME_MAX_LENGTH)
    private String nameAndSurname;

    @Column(name = "aboutme")
    private String aboutMe;

    @Column(name = "avatar")
    private String avatarPath;

    @Column(name = "privacy", nullable = false)
    private boolean privacy;

    @Column(name = "showprofile", nullable = false)
    private boolean showProfile;

    @Column(name = "isopennotification", nullable = false)
    private boolean isOpenNotification;

    public Profile() {
        super();
    }

    @Override
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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
        return null;
    }
}
