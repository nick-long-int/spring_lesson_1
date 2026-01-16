package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ContactServiceImpl implements ContactService {

    private final Resource resource;

    public ContactServiceImpl(@Value("${contact.path}")Resource resource) {
        this.resource = resource;
    }

    @Override
    public String findAll() {
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream())
            );
            StringBuilder sb = new StringBuilder();

            reader.lines().forEach(line -> sb.append(line).append("\n"));

            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
