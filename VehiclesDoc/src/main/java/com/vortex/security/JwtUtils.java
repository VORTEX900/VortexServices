package com.vortex.security;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
@EnableConfigurationProperties(VaultConfig.class)
//@ConditionalOnProperty(name = "security.enabled", havingValue = "true", matchIfMissing = true)
public class JwtUtils {

	private final VaultConfig vaultConfig;
	
	public JwtUtils(VaultConfig vaultConfig) {
		this.vaultConfig = vaultConfig;
	}
	
	private SecretKey getKey() {
        return Keys.hmacShaKeyFor(vaultConfig.getVortexSecretToken().getBytes(StandardCharsets.UTF_8));
    }

    // Questo metodo verifica il token e ritorna i claims decodificati
    public Claims validateTokenAndGetClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())   // imposta la chiave per verificare la firma
                .build()
                .parseClaimsJws(token)  // verifica firma e scadenza
                .getBody();
    }
}
