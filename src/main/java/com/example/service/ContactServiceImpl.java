package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ContactServiceImpl implements ContactService {

    @Value("${contact.path}")
    private Resource resource;

    @Override
    public void findAll() {
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream())
            );
            reader.lines().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
