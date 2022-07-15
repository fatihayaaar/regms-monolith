package com.fayardev.membershipsystem.validates;

import com.fayardev.membershipsystem.entities.Profile;
import com.fayardev.membershipsystem.exceptions.ProfileException;
import com.fayardev.membershipsystem.exceptions.enums.ErrorComponents;
import com.fayardev.membershipsystem.exceptions.enums.Errors;

public class ProfileValidate {

    public static boolean aboutMeValidate(String aboutMe) throws ProfileException {
        if (aboutMe.trim().isEmpty()) {
            throw new ProfileException("about me cannot be blank", Errors.NOT_NULL_ERROR, ErrorComponents.ABOUT_ME);
        }
        aboutMe = aboutMe.trim().toLowerCase();
        if (aboutMe.trim().length() > Profile.MAX_ABOUT_ME_LENGTH) {
            throw new ProfileException("300 karakterden uzun", Errors.MAX_VALUE_LENGTH, ErrorComponents.ABOUT_ME);
        }
        return true;
    }

    public static boolean nameAndSurnameValidate(String nameAndSurname) throws ProfileException {
        if (nameAndSurname.trim().isEmpty()) {
            throw new ProfileException("name and surname cannot be blank", Errors.NOT_NULL_ERROR, ErrorComponents.NAME_AND_SURNAME);
        }
        nameAndSurname = nameAndSurname.trim().toLowerCase();
        if (nameAndSurname.trim().length() > Profile.NAME_AND_SURNAME_MAX_LENGTH) {
            throw new ProfileException("32 karakterden uzun", Errors.MAX_VALUE_LENGTH, ErrorComponents.NAME_AND_SURNAME);
        }
        return true;
    }

    public static boolean profileValidate(Profile profile) throws ProfileException {
        if (!ProfileValidate.aboutMeValidate(profile.getAboutMe())) {
            return false;
        }
        return ProfileValidate.nameAndSurnameValidate(profile.getNameAndSurname());
    }
}
