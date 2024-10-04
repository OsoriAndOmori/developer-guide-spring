package com.skt.autoconfigure.bean;

import com.skt.autoconfigure.match.AlwaysMatchTrueCondition;
import com.skt.autoconfigure.annotation.MyCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//@MyCondition(AlwaysMatchTrueCondition.class)
public class TestAutoConfiguration {

    @Bean
    public RestTemplate myRestTemplateExist() {
        return new RestTemplate();
    }
}
