package com.example.springhw;

import com.example.service.UserService;
import com.example.service.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
@EnableCaching
public class SpringHwApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringHwApplication.class, args);
        UserService userService = context.getBean(UserServiceImpl.class);
        System.out.println(userService.findAll());
    }

}
