package com.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class KafkaMessagingService {

    @Bean
    public Consumer<Message<String>> consumer() {
        return message -> System.out.printf(
            "User with username: %s was %s", message.getPayload(), message.getHeaders().get("Action:"));
    }
}
