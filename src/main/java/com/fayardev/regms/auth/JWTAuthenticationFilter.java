package com.fayardev.regms.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fayardev.regms.auth.util.JWTUtil;
import com.fayardev.regms.dtos.AuthUserDto;
import com.fayardev.regms.entities.User;
import com.fayardev.regms.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

import static com.fayardev.regms.auth.AuthConstants.HEADER_STRING;
import static com.fayardev.regms.auth.AuthConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil JWTUtil;
    private final UserService userService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil JWTUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.JWTUtil = JWTUtil;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            AuthUserDto user = new ObjectMapper().readValue(req.getInputStream(), AuthUserDto.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        UserDetails userDetails = userService.loadUserByUsername(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
        String token = JWTUtil.generateToken(userDetails);
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
