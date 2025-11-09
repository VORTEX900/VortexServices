package com.vortex.security;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    private static final String SECRET_KEY = "gXv8qT9rN2w5ZyBhLmPsUkVeXy3a6DfGhJkLzMnBqRtVuWnYp9SrTcXbEzHgKrLv1"; // deve corrispondere all'Auth Service

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    	
    	System.out.println("FILTER TRIGGERED, path: " + exchange.getRequest().getPath().value());
    	
        String path = exchange.getRequest().getPath().value();

        // Salta la protezione per il login/register
        if (path.startsWith("/auth")) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // eventuali info aggiuntive dal JWT, se serve
        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }
}
