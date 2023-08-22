package com.fayardev.regms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Profile extends BaseEntity {

    public static final int MAX_ABOUT_ME_LENGTH = 300;
    public static final int NAME_AND_SURNAME_MAX_LENGTH = 32;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false, length = NAME_AND_SURNAME_MAX_LENGTH)
    private String nameAndSurname;

    private String aboutMe;
    private String avatarPath;

    @Column(nullable = false)
    private boolean privacy;

    @Column(nullable = false)
    private boolean showProfile;

    @Column(nullable = false)
    private boolean isOpenNotification;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(nullable = false)
    private User user;

    public Profile() {
        super();
    }

    @Override
    public Long getID() {
        return ID;
    }

    @Override
    public String toString() {
        return null;
    }
}
