package com.fayardev.regms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = "User.findByPhoneNo", query = "SELECT u FROM User u WHERE u.phoneNo=:phoneNo"),
        @NamedQuery(name = "User.findByEmailAddress", query = "SELECT u FROM User u WHERE u.emailAddress=:email"),
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username=:username"),
})
@JsonIgnoreProperties(allowSetters = true, value = {"password"})
public final class User extends BaseEntity {

    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 32;
    public static final int USERNAME_MAX_LENGTH = 11;
    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int EMAIL_ADDRESS_MAX_LENGTH = 32;
    public static final int PHONE_NO_MAX_LENGTH = 32;
    public static final int SEX_MAX_LENGTH = 8;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int ID;

    @Column(name = "username", nullable = false, length = USERNAME_MAX_LENGTH, unique = true)
    private String username;

    @Column(name = "emailaddress", nullable = false, length = EMAIL_ADDRESS_MAX_LENGTH, unique = true)
    private String emailAddress;

    @Column(name = "phoneno", length = PHONE_NO_MAX_LENGTH, unique = true)
    private String phoneNo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    @Column(name = "password", nullable = false)
    @Type(type = "text")
    private String password;

    @Column(name = "sex", nullable = false, length = SEX_MAX_LENGTH)
    private String sex;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate", nullable = false)
    private Date birthDate;

    @Column(name = "confirm", nullable = false)
    private boolean confirm;

    @Column(name = "verified", nullable = false)
    private boolean verified;

    @Column(name = "isactive", nullable = false)
    private boolean isActive;

    @Formula("YEAR(CURDATE()) - YEAR(birthdate)")
    private String age;

    @Temporal(TemporalType.DATE)
    @Column(name = "createdate", nullable = false)
    private Date createDate;

    public User() {
        super();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
