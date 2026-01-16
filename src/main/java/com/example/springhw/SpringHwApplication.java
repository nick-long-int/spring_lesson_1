package com.example.springhw;

import com.example.service.ContactService;
import com.example.service.ContactServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.service"})
public class SpringHwApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringHwApplication.class, args);
        ContactService service = context.getBean(ContactServiceImpl.class);
        System.out.println(service.findAll());
    }

}
