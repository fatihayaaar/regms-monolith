package com.fayardev.regms.dtos;

import com.fayardev.regms.entities.User;
import com.fayardev.regms.validates.UserValidate;
import lombok.*;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
}
