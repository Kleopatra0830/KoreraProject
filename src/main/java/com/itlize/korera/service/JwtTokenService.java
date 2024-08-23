package com.itlize.korera.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import com.itlize.korera.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
@Service
public class JwtTokenService {

    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    String secretKey = Encoders.BASE64.encode(key.getEncoded());
    private final String SECRET_KEY = secretKey;

    public String generateToken(User user, boolean rememberMe) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, user.getUsername(), rememberMe);
    }

    @SuppressWarnings("deprecation")
    private String createToken(Map<String, Object> claims, String subject, boolean rememberMe) {
        long expirationTime = rememberMe 
            ? 1000 * 60 * 60 * 24 * 7  // 7 days expiration for "Remember Me"
            : 1000 * 60 * 60 * 10;     // 10 hours expiration for regular login
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) 
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, User user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    @SuppressWarnings("deprecation")
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
