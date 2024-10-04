package com.skt.autoconfigure.bean;

import com.skt.autoconfigure.annotation.MyBooleanCondition;
import com.skt.autoconfigure.annotation.MyPropertyCondition;
import com.skt.autoconfigure.match.AlwaysMatchFalseFiltering;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@MyPropertyCondition(target="my.test3.autoconfig", value = "on")
public class Test3AutoConfiguration {

    @Bean
    public RestTemplate myRestTemplateByProperty() {
        return new RestTemplate();
    }
}
