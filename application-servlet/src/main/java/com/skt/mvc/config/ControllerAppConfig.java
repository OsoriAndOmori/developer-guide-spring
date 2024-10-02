package com.skt.mvc.config;

import com.skt.mvc.controller.SampleController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// @ComponentScan
public class ControllerAppConfig {

    @Bean
    public SampleController sampleController() {
        return new SampleController();
    }
}
