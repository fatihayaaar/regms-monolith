package com.fayardev.regms.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "PasswordResetToken.findByToken", query = "SELECT u FROM PasswordResetToken u WHERE u.token=:token"),
        @NamedQuery(name = "PasswordResetToken.findByEmail", query = "SELECT u FROM PasswordResetToken u WHERE u.emailAddress=:emailAddress and u.isActive=true order by u.expiryDate desc "),
        @NamedQuery(name = "PasswordResetToken.findByTokenPassword", query = "SELECT u FROM PasswordResetToken u WHERE u.tokenPassword=:tokenPassword"),
})
@Table(name = "passwordresettoken")
public final class PasswordResetToken extends BaseEntity {

    private static final int EXPIRATION = 2;
    public static final int TOKEN_EXPIRATION = 60 * 2 * 60;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "token")
    private String token;

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

    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public PasswordResetToken() {

    }

    public static int getEXPIRATION() {
        return EXPIRATION;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return null;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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