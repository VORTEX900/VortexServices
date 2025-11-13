package com.vortex.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("vault-config")
public class VaultConfig {
	
	private String vortexSecretToken;

	public String getVortexSecretToken() {
		return vortexSecretToken;
	}

	public void setVortexSecretToken(String vortexSecretToken) {
		this.vortexSecretToken = vortexSecretToken;
	}

}
