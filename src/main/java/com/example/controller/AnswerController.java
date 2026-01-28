package com.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final StreamBridge streamBridge;

    @GetMapping("/time")
    public ResponseEntity<String> askWhatTimeItIs(){
        streamBridge.send("question-out-0",
            MessageBuilder.withPayload("What time is it?").build());
        return ResponseEntity.ok().body("Time received. Check the console.");
    }

}
