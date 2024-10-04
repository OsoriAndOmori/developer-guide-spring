package com.skt.mvc.annotation;

import com.skt.autoconfigure.AutoImportSelector;
import com.skt.autoconfigure.annotation.자동설정사용해보자;
import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) //요게 있어야함 꼭
@Documented
@ComponentScan
@자동설정사용해보자(AutoImportSelector.class)
public @interface MyApplication {
}
