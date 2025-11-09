package com.vortex.config;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocator locator = builder.routes()
            .route("vehicle-service", r -> r.path("/vehicle/**")
                .filters(f -> f.stripPrefix(0)) 
                //.uri("http://localhost:8081"))//locale
            	.uri("http://vehicles-service:8081"))//docker
            .route("expiration-service", r -> r.path("/expiration/**")
            	    .filters(f -> f.stripPrefix(0))
            	    //.uri("http://localhost:8082"))//locale
            		.uri("http://expiration-service:8082"))//docker
            .build();

        locator.getRoutes().subscribe(route -> System.out.println("Registered route: " + route.getId()));

        return locator;
    }
}