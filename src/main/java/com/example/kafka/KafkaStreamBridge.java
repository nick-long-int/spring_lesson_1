package com.example.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaStreamBridge {

    private final StreamBridge streamBridge;

    public <T> void sendUserWasRegister(String topic, T message) {
        Message<T> msg = MessageBuilder
            .withPayload(message)
            .setHeader("Action:", "Created")
            .build();

        streamBridge.send(topic, msg);
    }
}
