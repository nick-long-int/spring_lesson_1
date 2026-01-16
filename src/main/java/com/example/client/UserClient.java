package com.example.client;

import com.example.dto.UserResponseDto;
import feign.RequestLine;

public interface UserClient {
    @RequestLine("GET /api")
    UserResponseDto findAll();
}
