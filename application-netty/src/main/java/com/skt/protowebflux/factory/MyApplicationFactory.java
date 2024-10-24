package com.skt.protowebflux.factory;

import com.skt.protowebflux.config.MyWebFluxConfiguration;
import com.skt.protowebflux.handler.SimpleHandler;
import com.skt.protowebflux.router.RouterConfig;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebServerApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.DispatcherHandler;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.netty.http.server.HttpServer;

public class MyApplicationFactory {
    public static void run(Class<?> Class, String[] args) {
        AnnotationConfigReactiveWebServerApplicationContext ac = new AnnotationConfigReactiveWebServerApplicationContext();
        ac.register(Class);
        ac.register(MyWebFluxConfiguration.class);
        ac.refresh();

        DispatcherHandler dispatcherHandler = new DispatcherHandler(ac);
        // WebHttpHandlerBuilder를 사용하여 HttpHandler 생성
        HttpHandler build = WebHttpHandlerBuilder
                .webHandler(dispatcherHandler)
                .build();

        HttpServer.create()
                .port(8080)
                .handle(new ReactorHttpHandlerAdapter(build))
                .bindNow()
                .onDispose()
                .block();
    }
}
