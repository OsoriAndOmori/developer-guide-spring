package com.skt.autoconfigure.bean;

import com.skt.autoconfigure.annotation.MyBooleanCondition;
import com.skt.autoconfigure.match.AlwaysMatchTrueFiltering;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@MyBooleanCondition(AlwaysMatchTrueFiltering.class)
public class Test1AutoConfiguration {

    @Bean
    public RestTemplate myRestTemplateExist() {
        return new RestTemplate();
    }
}
