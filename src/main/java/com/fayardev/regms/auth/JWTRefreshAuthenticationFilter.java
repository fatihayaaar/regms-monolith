package com.fayardev.regms.auth;

import com.fayardev.regms.auth.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

public class JWTRefreshAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JWTUtil JWTUtil;

    public JWTRefreshAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil JWTUtil) {
        this.JWTUtil = JWTUtil;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/refresh", "POST"));
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        String refreshToken = obtainRefreshToken(req);

        if (refreshToken != null) {
            return getAuthenticationManager().authenticate(new RefreshToken(refreshToken));
        }
        throw new RuntimeException("Refresh token is missing.");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String newAccessToken = JWTUtil.generateRefreshToken((UserDetails) authResult.getPrincipal());
        response.addHeader("Authorization", "Bearer " + newAccessToken);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed: " + failed.getMessage());
    }

    private String obtainRefreshToken(HttpServletRequest request) {
        return request.getHeader("Refresh-Token");
    }
}
