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
                .filters(f -> f.stripPrefix(0)) // attenzione a stripPrefix se vuoi mantenere /vehicle
                .uri("http://localhost:8081"))
            .build();

        locator.getRoutes().subscribe(route -> System.out.println("Registered route: " + route.getId()));

        return locator;
    }
}