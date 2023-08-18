package com.fayardev.regms.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class RefreshToken extends UsernamePasswordAuthenticationToken {

    private String refreshToken;

    public RefreshToken(String refreshToken) {
        super(null, null);
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
