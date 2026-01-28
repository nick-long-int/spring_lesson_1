package com.example.service;

import java.util.function.Consumer;

public class ChatServiceImpl implements ChatService {
    @Override
    public Consumer<String> consumer() {
        return message -> {
            System.out.println();
        };
    }
}
