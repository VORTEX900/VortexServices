package com.vortex.security;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class JwtHeaderPropagationFilter implements GatewayFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .switchIfEmpty(Mono.empty())
                .flatMap(auth -> {

                    if (auth == null) {
                        return chain.filter(exchange);
                    }

                    String userId = (String) auth.getPrincipal();

                    // aggiungo l’header verso i microservizi
                    ServerWebExchange mutated = exchange.mutate()
                            .request(builder -> builder.header("X-User-Id", userId))
                            .build();

                    return chain.filter(mutated);
                });
    }
}