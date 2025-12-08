package com.vortex.config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vortex.security.JwtHeaderPropagationFilter;

@Configuration
public class GatewayConfig {
	
	@Value("${gateway.vehicle-uri}")
	private String vehicleUri;

	@Value("${gateway.expiration-uri}")
	private String expirationUri;
	
	Logger log = LogManager.getLogger(GatewayConfig.class);

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, JwtHeaderPropagationFilter headerFilter) {
        RouteLocator locator = builder.routes()
            .route("vehicle-service", r -> r.path("/vehicle/**")
                .filters(f -> f.stripPrefix(0).filter(headerFilter)
                		) 
                .uri(vehicleUri))
                //.uri("http://localhost:8081"))//locale
            	//.uri("http://vehicles-service:8081"))//docker
            .route("expiration-service", r -> r.path("/expiration/**")
            	    .filters(f -> f.stripPrefix(0).filter(headerFilter)
            		) 
            	    .uri(expirationUri))
            	    //.uri("http://localhost:8082"))//locale
            		//.uri("http://expiration-service:8082"))//docker
            .build();

        locator.getRoutes().subscribe(route -> log.info("Registered route: {}", route.getId()));

        return locator;
    }
}