package com.vortex.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
@EnableConfigurationProperties(VaultConfig.class)
public class JwtUtils {

	private final VaultConfig vaultConfig;
	
	Logger log = LogManager.getLogger(JwtUtils.class);
	
	public JwtUtils(VaultConfig vaultConfig) {
		this.vaultConfig = vaultConfig;
	}
	
    private static final long JWR_EXPIRATION_MS = 86400000; // 1 giorno

    // Metodo helper: converte la secret in chiave compatibile
    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(vaultConfig.getVortexSecretToken().getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwtToken(UserDetailsImpl userPrincipal) {
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWR_EXPIRATION_MS))
                .signWith(getKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        }
        return false;
    }
}
