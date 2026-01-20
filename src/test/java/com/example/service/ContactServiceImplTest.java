package com.example.service;

import com.example.dto.ContactDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = ContactServiceImpl.class)
@ActiveProfiles("test")
@DisplayName("Сервис контактов")
class ContactServiceImplTest {

    private ContactService contactService;

    @BeforeEach
    void setUp(){
        Map<String, ContactDto> contactMap = new HashMap<>();
        ContactDto contactDto = new ContactDto();
        contactDto.setPhone("7909");
        contactDto.setFirstName("Test");
        contactDto.setLastName("Test");
        contactDto.setMiddleName("Test");

        contactMap.put(contactDto.getPhone(), contactDto);

        contactService = new ContactServiceImpl(contactMap);
    }

    @Test
    @DisplayName("Добавление контакта")
    void testCreateContact() {
        ContactDto contactDto = new ContactDto();
        contactDto.setPhone("Test");

        contactService.createContact(contactDto);
        assertEquals(2, contactService.findAll().size());
    }

    @Test
    @DisplayName("Удаление контакта")
    void testDeleteContact() {
        String phone = "7909";

        contactService.deleteContactByPhone(phone);

        assertEquals(0, contactService.findAll().size());
    }

    @Test
    @DisplayName("Обновление контакта")
    void testUpdateContact() {
        ContactDto contactDto = new ContactDto();
        contactDto.setPhone("7999");
        String phone = "7909";

        ContactDto contact =
            contactService.updateContactByPhone(phone, contactDto);

        assertEquals(phone, contact.getPhone());
    }

    @Test
    @DisplayName("Найти контакт по телефону")
    void testFindByPhone() {
        String phone = "7909";
        String firstName = "Test";

        ContactDto contactDto =
            contactService.findContactByPhone(phone);

        assertEquals(phone, contactDto.getPhone());
        assertEquals(firstName, contactDto.getFirstName());
    }

    @Test
    @DisplayName("Контакт не найден")
    void testFindByPhoneWhenContactNotFound() {
        String phone = "7908";

        ContactDto contactDto =
            contactService.findContactByPhone(phone);

        assertNull(contactDto);
    }

}