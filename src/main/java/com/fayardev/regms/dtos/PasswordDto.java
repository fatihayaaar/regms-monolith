package com.fayardev.regms.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PasswordDto extends BaseDto {

    private String oldPassword;
    private String token;
    private String validateCode;
    private String emailAddress;
    private String newPassword;
}