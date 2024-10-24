package com.skt.netty.reactor;

import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

public class ReactorNettyServer {

    public static void main(String[] args) {
        HttpServer.create()  // 서버 인스턴스 생성
                .port(8080)  // 8080 포트에서 요청 수신
                .route(routes ->  // 라우트 정의
                        routes.get("/hello", (request, response) ->
                                        response.sendString(Mono.just("Hello from Reactor Netty!"))
                                )
                                .get("/goodbye", (request, response) ->
                                        response.sendString(Mono.just("Goodbye from Reactor Netty!"))
                                )
                )
                .bindNow()  // 서버 바인딩 및 실행
                .onDispose()  // 서버 종료까지 대기
                .block();
    }
}