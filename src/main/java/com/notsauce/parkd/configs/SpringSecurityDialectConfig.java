package com.notsauce.parkd.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
public class SpringSecurityDialectConfig {
    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }
}
