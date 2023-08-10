package com.fayardev.regms.dtos;

import com.fayardev.regms.entities.User;
import com.fayardev.regms.validates.UserValidate;
import lombok.Builder;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.Date;

public class AuthUserDto extends BaseDto {

    @NotEmpty
    @NotBlank
    @Size(min = User.USERNAME_MIN_LENGTH, max = User.USERNAME_MAX_LENGTH)
    @Pattern(regexp = UserValidate.USERNAME_PATTERN)
    private String username;

    @NotEmpty
    @Size(max = User.EMAIL_ADDRESS_MAX_LENGTH)
    @Email
    private String emailAddress;

    @Size(max = User.PHONE_NO_MAX_LENGTH)
    private String phoneNo;

    @NotEmpty
    @NotBlank
    @Size(min = User.PASSWORD_MIN_LENGTH, max = User.PASSWORD_MAX_LENGTH)
    @Pattern(regexp = UserValidate.PASSWORD_PATTERN)
    private String password;

    @NotEmpty
    @NotBlank
    private String sex;

    @NotEmpty
    @NotBlank
    private Timestamp birthDate;

    private Date createDate;
    private boolean isActive;
    private boolean verified;
    private boolean confirm;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }
}
