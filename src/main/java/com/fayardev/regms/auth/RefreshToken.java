package com.fayardev.regms.auth;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
public class RefreshToken {

    @NotNull
    @NotBlank
    private String refreshToken;
}
