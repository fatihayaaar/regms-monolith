package com.fayardev.regms.dtos;

import com.fayardev.regms.entities.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RefreshTokenDto extends BaseDto {

    @NotNull
    @NotBlank
    private String token;

    @NotNull
    @NotBlank
    private User user;

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
}
