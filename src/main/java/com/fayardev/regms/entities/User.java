package com.fayardev.regms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
@JsonIgnoreProperties(allowSetters = true, value = {"password"})
public class User extends BaseEntity {

    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 32;
    public static final int USERNAME_MAX_LENGTH = 11;
    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int EMAIL_ADDRESS_MAX_LENGTH = 32;
    public static final int PHONE_NO_MAX_LENGTH = 32;
    public static final int SEX_MAX_LENGTH = 8;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false, length = USERNAME_MAX_LENGTH, unique = true)
    private String username;

    @Column(nullable = false, length = EMAIL_ADDRESS_MAX_LENGTH, unique = true)
    private String emailAddress;

    @Column(length = PHONE_NO_MAX_LENGTH, unique = true)
    private String phoneNo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = SEX_MAX_LENGTH)
    private String sex;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    private boolean confirm;

    @Column(nullable = false)
    private boolean verified;

    @Column(nullable = false)
    private boolean isActive;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date createDate;

    @Transient
    private Integer age;

    public User() {
        super();
    }

    @Override
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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

    public Integer getAge() {
        if (birthDate != null) {
            LocalDate currentDate = LocalDate.now();
            return Period.between(birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), currentDate).getYears();
        }
        return null;
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

    public void setPassword(String hashPassword) {
        this.password = hashPassword;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User {" +
                "'id': " + this.ID + ", " +
                "'username' : '"+ this.username +"', " +
                "'emailAddress' : '"+ this.emailAddress +"', " +
                "'sex' : '"+ this.sex +"', " +
                "}";
    }
}
