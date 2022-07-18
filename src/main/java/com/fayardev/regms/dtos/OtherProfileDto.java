package com.fayardev.regms.dtos;

public class OtherProfileDto {

    private String nameAndSurname;
    private String aboutMe;
    private String avatarPath;
    private OtherUserDto otherUserDto;

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

    public OtherUserDto getOtherUserDto() {
        return otherUserDto;
    }

    public void setOtherUserDto(OtherUserDto otherUserDto) {
        this.otherUserDto = otherUserDto;
    }
}
