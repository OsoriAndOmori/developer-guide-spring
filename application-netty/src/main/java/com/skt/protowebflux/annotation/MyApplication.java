package com.skt.protowebflux.annotation;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) //요게 있어야함 꼭
@Documented
@ComponentScan
public @interface MyApplication {
}
