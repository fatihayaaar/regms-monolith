package com.fayardev.regms.dtos;

public class OtherProfileMiniDto {

    private String nameAndSurname;
    private String aboutMe;
    private String avatarPath;
    private OtherUserMiniDto otherUserMiniDto;

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

    public OtherUserMiniDto getOtherUserMiniDto() {
        return otherUserMiniDto;
    }

    public void setOtherUserMiniDto(OtherUserMiniDto otherUserMiniDto) {
        this.otherUserMiniDto = otherUserMiniDto;
    }
}
