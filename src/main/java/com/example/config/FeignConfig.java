package com.example.config;

import com.example.client.UserClient;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public UserClient createUserClient() {
        return Feign.builder()
            .encoder(new JacksonEncoder())
            .decoder(new JacksonDecoder())
            .logger(new Slf4jLogger())
            .logLevel(feign.Logger.Level.FULL)
            .target(UserClient.class, "https://randomuser.me");
    }

}
