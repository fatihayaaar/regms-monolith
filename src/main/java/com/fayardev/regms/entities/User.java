package com.fayardev.regms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fayardev.regms.auth.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Getter
@Setter
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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Timestamp birthDate;

    @Column(nullable = false)
    private boolean confirm;

    @Column(nullable = false)
    private boolean verified;

    @Column(nullable = false)
    private boolean isActive;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Timestamp createDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Transient
    private Integer age;

    public User() {
        super();
    }

    @PrePersist
    protected void onCreate() {
        createDate = new Timestamp((new Date()).getTime());
    }

    @Override
    public Long getID() {
        return ID;
    }

    @Override
    public String toString() {
        return null;
    }

    public Integer getAge() {
        if (birthDate != null) {
            LocalDate currentDate = LocalDate.now();
            return Period.between(birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), currentDate).getYears();
        }
        return null;
    }
}
