package com.example.Immo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); // clé sécurisée
    private static final long EXPIRATION_TIME = 86400000; // 24 heures

    // Générer un token JWT
    public String generateJwtToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    // Extraire le nom d’utilisateur (email)
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Vérifier si le token est expiré
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Extraire toutes les informations du token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Vérifier la validité du token pour un utilisateur
    public boolean validateToken(String token, String email) {
        String username = extractUsername(token);
        return (username.equals(email) && !isTokenExpired(token));
    }
}
