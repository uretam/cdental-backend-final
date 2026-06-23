package com.cdental.auth_service.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    // La firma HMAC-SHA requiere una clave de minimo 256 bits (32 caracteres)
    private final String SECRET_KEY = "ClinicaDentalSecretKeyParaJWT2026_KISS_Approach";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // Expira en 1 hora
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}