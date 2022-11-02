package com.youlianmei.config;

import com.youlianmei.model.LoginProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBeanConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "login")

    public LoginProperties loginProperties() {

        return new LoginProperties();
    }
}
