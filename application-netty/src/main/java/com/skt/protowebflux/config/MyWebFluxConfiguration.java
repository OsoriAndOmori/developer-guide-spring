package com.skt.protowebflux.config;

import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.ReactiveWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ReactiveWebServerFactoryAutoConfiguration.class, WebFluxAutoConfiguration.class, HttpHandlerAutoConfiguration.class})
public class MyWebFluxConfiguration {
}
