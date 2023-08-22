package com.fayardev.regms.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto extends BaseDto {

    private String username;
    private String emailAddress;
    private String phoneNo;
    private String sex;
    private Timestamp birthDate;
    private Timestamp createDate;
    private boolean isActive;
    private boolean verified;
    private boolean confirm;
}
