package com.skt.protowebflux.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SimpleHandler {
    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().bodyValue("Hello from Spring Router!");
    }

    public Mono<ServerResponse> goodbye(ServerRequest request) {
        return ServerResponse.ok().bodyValue("Goodbye from Spring Router!");
    }
}
