package com.example.service.assymetrics;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

@Service
@RequiredArgsConstructor
public class JwtValidator {
    private final PublicKey publicKey;

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public String extractRole(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("role", String.class);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(publicKey)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }


}
