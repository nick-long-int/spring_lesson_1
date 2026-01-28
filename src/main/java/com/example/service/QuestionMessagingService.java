package com.example.service;

import com.example.kafka.KafkaStreamBridge;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class QuestionMessagingService {
    private final StreamBridge streamBridge;

    @Bean
    public Consumer<Message<String>> questionProcessor(){
        return message -> {
            String question = message.getPayload();
            Message<String> answer = MessageBuilder
                .withPayload(
                    LocalDateTime.now().getHour()
                        + ":" +
                        LocalDateTime.now().getMinute()
                )
                .setHeader("Question: ", question)
                .build();


            streamBridge.send("answer-out-0", answer);
        };
    }

    @Bean
    public Consumer<Message<String>> answerSout(){
        return message -> {
            System.out.printf("Question: %s\n" +
                "Answer: %s\n", message.getHeaders().get("Question: "), message.getPayload());
        };
    }

}
