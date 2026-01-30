package com.example.service;

import com.example.dto.UserDto;

import java.util.Collection;

public interface UserService {
    Collection<UserDto> findAll();
}
