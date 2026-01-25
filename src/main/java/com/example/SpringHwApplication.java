package com.example;

import com.example.service.UserService;
import com.example.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class SpringHwApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringHwApplication.class, args);
        UserService userService = context.getBean(UserServiceImpl.class);
        log.info(userService.findAll().toString());
    }

}
