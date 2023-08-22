package com.fayardev.regms.dtos;

import com.fayardev.regms.entities.User;
import jakarta.persistence.Entity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RefreshTokenDto extends BaseDto {

    @NotNull
    @NotBlank
    private String token;

    private String accessToken;

    @NotNull
    @NotBlank
    private User user;
}
