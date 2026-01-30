package com.example.service;

import com.example.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DisplayName("Сервис получения пользователя")
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Получить пользователя по feign клиенту")
    void testFindAll(){

        UserDto userDto = userService.findAll().stream().findFirst().orElse(null);

        assertNotNull(userDto);
        assertNotNull(userDto.getGender());
        assertNotNull(userDto.getName().getFirst());
        assertNotNull(userDto.getName().getLast());
        assertNotNull(userDto.getName().getTitle());
    }

}