package com.fayardev.regms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NamedQueries(value = {
        @NamedQuery(name = "PasswordReset.findByEmail", query = "SELECT pr FROM PasswordReset pr WHERE pr.emailAddress=:emailAddress and pr.isActive=true order by pr.expiryDate desc "),
})
public class PasswordReset extends BaseEntity {

    public static final int TOKEN_EXPIRATION = 60 * 2 * 60;
    public static final int EXPIRATION = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String validateCode;
    private String emailAddress;
    private int numberOfInteractions;
    private boolean isActive;
    private Date expiryDate;
    private String tokenPassword;
    private boolean isActiveTokenPassword;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private User user;

    public PasswordReset(String validateCode, User user) {
        this.validateCode = validateCode;
        this.user = user;
    }

    public PasswordReset() {
        super();
    }

    @Override
    public String toString() {
        return null;
    }
}