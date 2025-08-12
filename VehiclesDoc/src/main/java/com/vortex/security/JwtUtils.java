package com.vortex.security;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    private final String jwtSecret = "gXv8qT9rN2w5ZyBhLmPsUkVeXy3a6DfGhJkLzMnBqRtVuWnYp9SrTcXbEzHgKrLv1"; // 64 chars = 512 bit!;
 // Usa la stessa chiave segreta con cui il microservizio Y ha firmato il token
    private final Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    // Questo metodo verifica il token e ritorna i claims decodificati
    public Claims validateTokenAndGetClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)   // imposta la chiave per verificare la firma
                .build()
                .parseClaimsJws(token)  // verifica firma e scadenza
                .getBody();
    }
}
