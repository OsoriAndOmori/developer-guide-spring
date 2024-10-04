package com.skt.autoconfigure.bean;

import com.skt.autoconfigure.match.AlwaysMatchFalseCondition;
import com.skt.autoconfigure.annotation.MyCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//@MyCondition(AlwaysMatchFalseCondition.class)
public class Test2AutoConfiguration {

    @Bean
    public RestTemplate myRestTemplateNotExist() {
        return new RestTemplate();
    }
}
