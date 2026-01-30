package com.example.service;

import com.example.client.UserClient;
import com.example.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserClient userClient;

    @Override
    public Collection<UserDto> findAll() {
        return userClient.findAll().getResults();
    }
}
