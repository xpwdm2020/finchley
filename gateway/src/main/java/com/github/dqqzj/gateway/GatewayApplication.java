package com.github.dqqzj.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@SpringCloudApplication
public class GatewayApplication {

    @Autowired
    FallbackHandler fallbackHandler;

    @Bean
    public RouterFunction<?> routerFunction(){
        return RouterFunctions.route(RequestPredicates.GET("/fallback").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),fallbackHandler::fallback);
    }
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
