package com.skt.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.skt.mvc.repository")
@Configuration
public class JpaConfig {
}
