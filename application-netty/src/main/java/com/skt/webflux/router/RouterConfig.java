package com.skt.webflux.router;

import com.skt.webflux.handler.SimpleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {
    private final SimpleHandler simpleHandler;

    public RouterConfig(SimpleHandler simpleHandler) {
        this.simpleHandler = simpleHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route()
                .GET("/hello", simpleHandler::hello)
                .GET("/goodbye", simpleHandler::goodbye)
                .build();
    }
}