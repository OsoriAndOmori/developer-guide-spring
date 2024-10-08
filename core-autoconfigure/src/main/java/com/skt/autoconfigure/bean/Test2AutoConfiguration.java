package com.skt.autoconfigure.bean;

import com.skt.autoconfigure.annotation.MyBooleanCondition;
import com.skt.autoconfigure.match.AlwaysMatchFalseFiltering;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@MyBooleanCondition(AlwaysMatchFalseFiltering.class)
public class Test2AutoConfiguration {

    @Bean
    public RestTemplate myRestTemplateNotExist() {
        return new RestTemplate();
    }
}
