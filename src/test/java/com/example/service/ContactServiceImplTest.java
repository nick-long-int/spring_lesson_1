package com.example.service;

import com.example.dto.ContactDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ActiveProfiles("prod")
@ContextConfiguration(classes = ContactServiceImpl.class, loader = AnnotationConfigContextLoader.class)
class ContactServiceImplTest {

    private ContactService contactService;

    @BeforeEach
    void setUp(){
        contactService = new ContactServiceImpl();
    }

    @Test
    void testFindByPhone(){

        ContactDto contactDto = new ContactDto();
        contactDto.setFirstName("TEST");
        contactDto.setLastName("Doe");
        contactDto.setMiddleName("Doe");
        contactDto.setPhone("7999");

        contactService.createContact(contactDto);

        Assertions.assertEquals("TEST", contactService.findContactByPhone("7999").getFirstName());

    }

    @Test
    void testFindByPhoneWhereInstanceNotExist(){

        Assertions.assertNull(contactService.findContactByPhone("7999"));

    }

}