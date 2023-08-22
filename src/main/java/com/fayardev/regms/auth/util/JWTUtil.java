package com.fayardev.regms.auth.util;

import com.auth0.jwt.JWT;
import com.fayardev.regms.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.security.Key;
import java.security.KeyStore;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class JWTUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = -2550185165626007488L;

    @Value("${server.ssl.key-store}")
    private String keyStoreClassPath;

    @Value("secret-key")
    private String keyName;

    @Value("${server.ssl.key-store-password}")
    private String keyStorePassword;

    @Value("${server.ssl.key-password}")
    private String keyPassword;

    @Value("${jwt.accessToken.expirationMs}")
    private Long accessTokenExpirationMs;

    @Value("${jwt.refreshToken.expirationMs}")
    private Long refreshTokenExpirationMs;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername(), accessTokenExpirationMs);
    }

    public String generateTokenByUsername(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username, accessTokenExpirationMs);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, Long expirationMs) {
        try {
            return JWT.create()
                    .withSubject(subject)
                    .withExpiresAt(new Date(System.currentTimeMillis() + expirationMs))
                    .sign(HMAC512(secretKey().getEncoded()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Key secretKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(getClass().getClassLoader().getResourceAsStream("keystore.jks"), keyStorePassword.toCharArray());

        return keyStore.getKey(keyName, keyPassword.toCharArray());
    }
}