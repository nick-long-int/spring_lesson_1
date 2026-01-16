package com.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceImplTest {

    private ContactService contactService;

    @BeforeEach
    void setUp() {
        Resource resource = new ClassPathResource("test.csv");
        contactService = new ContactServiceImpl(resource);
    }

    @Test
    void testFindAllContacts() {
        String correct = """
            LastName,FirstName,MiddleName,Phone
            Leonov,Alexey,Vladimirovich,79990190299
            Ivanov,Oleg,Petrovich,79530191296
            Stepanov,Petr,Ivanovich,79881233245
            Petrakov,Alexandr,Alexeyevich,79532111296
            Ivanova,Oksana,Vladimirocna,79522191213
            """;

        assertEquals(correct, contactService.findAll());

    }
}