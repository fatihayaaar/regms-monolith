package com.fayardev.regms.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "PasswordReset.findByValidateCode", query = "SELECT pr FROM PasswordReset pr WHERE pr.validateCode=:validateCode"),
        @NamedQuery(name = "PasswordReset.findByEmail", query = "SELECT pr FROM PasswordReset pr WHERE pr.emailAddress=:emailAddress and pr.isActive=true order by pr.expiryDate desc "),
        @NamedQuery(name = "PasswordReset.findByTokenPassword", query = "SELECT pr FROM PasswordReset pr WHERE pr.tokenPassword=:tokenPassword"),
})
@Table(name = "passwordreset")
public final class PasswordReset extends BaseEntity {

    public static final int TOKEN_EXPIRATION = 60 * 2 * 60;
    public static final int EXPIRATION = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "validatecode")
    private String validateCode;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userid")
    private User user;

    @Column(name = "emailaddres")
    private String emailAddress;

    @Column(name = "numberofinteractions")
    private int numberOfInteractions;

    @Column(name = "isactive")
    private boolean isActive;

    @Column(name = "expirydate")
    private Date expiryDate;

    @Column(name = "tokenpassword")
    private String tokenPassword;

    @Column(name = "isactivetokenpassword")
    private boolean isActiveTokenPassword;

    public PasswordReset(String validateCode, User user) {
        this.validateCode = validateCode;
        this.user = user;
    }

    public PasswordReset() {

    }

    public String getTokenPassword() {
        return tokenPassword;
    }

    public void setTokenPassword(String tokenPassword) {
        this.tokenPassword = tokenPassword;
    }

    public boolean isActiveTokenPassword() {
        return isActiveTokenPassword;
    }

    public void setActiveTokenPassword(boolean activeTokenPassword) {
        isActiveTokenPassword = activeTokenPassword;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getNumberOfInteractions() {
        return numberOfInteractions;
    }

    public void setNumberOfInteractions(int numberOfInteractions) {
        this.numberOfInteractions = numberOfInteractions;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return null;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String token) {
        this.validateCode = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}