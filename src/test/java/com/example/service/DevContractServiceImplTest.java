package com.example.service;

import com.example.dto.ContactDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
@ContextConfiguration(classes = DevContractServiceImpl.class, loader = AnnotationConfigContextLoader.class)
class DevContractServiceImplTest {

    private ContactService contactService;

    @BeforeEach
    void setUp() {
        contactService = new DevContractServiceImpl();
    }

    @Test
    void testDevProfile() {

        List<ContactDto> contactDtos = contactService.findAll();
        assertTrue(contactDtos.isEmpty());
        assertNull(contactService.findContactByPhone("7"));

    }

}