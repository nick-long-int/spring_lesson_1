package com.example.service;

import com.example.dto.ContactDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest
@DisplayName("Сервис контактов")
@Transactional
class ContactServiceImplTest {

    @Autowired
    private ContactService contactService;

    @BeforeEach
    @Sql("/data.sql")
    void setUp() {}

    @AfterEach
    @Sql("/data-remove.sql")
    void tearDown() {}

    @Test
    @DisplayName("Создание контакта")
    void createContactTest(){
        ContactDto createContactDto = new ContactDto();
        createContactDto.setFirstName("test_name");
        createContactDto.setLastName("test_last_name");
        createContactDto.setMiddleName("test_middle_name");
        createContactDto.setPhone("89129999998");

        createContactDto = contactService.createContact(createContactDto);

        assertNotNull(createContactDto);
        assertNotNull(createContactDto.getId());
    }

    @Test
    @DisplayName("Найти все контакты")
    void findAllContactTest(){
        List<ContactDto> contactDtos = contactService.findAll();

        assertNotNull(contactDtos);
        assertFalse(contactDtos.isEmpty());
    }

    @Test
    @DisplayName("Обновление контакта")
    void updateContactTest(){
        ContactDto updateContactDto = new ContactDto();
        updateContactDto.setFirstName("update_name");

        updateContactDto = contactService
            .updateContactByPhone("89129999999", updateContactDto);

        assertNotNull(updateContactDto);
        assertNotNull(updateContactDto.getId());
        assertEquals("update_name", updateContactDto.getFirstName());
    }

    @Test
    @DisplayName("Обновление контакта с несуществующим номером")
    void updateContactWithWrongPhoneTest(){
        ContactDto updateContactDto = new ContactDto();
        updateContactDto.setFirstName("update_name");

        String wrongPhone = "800";

        assertThrows(NullPointerException.class, () -> {
            contactService.updateContactByPhone(wrongPhone, updateContactDto);
        });
    }

    @Test
    @DisplayName("Получение контакта по номеру")
    void findContactByPhoneTest(){
        ContactDto contactDto = contactService.findContactByPhone("89129999999");

        assertNotNull(contactDto);
        assertNotNull(contactDto.getId());
        assertEquals("89129999999", contactDto.getPhone());
    }

    @Test
    @DisplayName("Попытка получить контакт по несуществующему номеру")
    void findContactByWrongPhoneTest(){
        String wrongPhone = "800";

        assertThrows(NullPointerException.class, () -> {
            contactService.findContactByPhone(wrongPhone);
        });
    }

    @Test
    @DisplayName("Удаление контакта")
    void deleteContactTest(){
        String phone = "89129999999";
        contactService.deleteContactByPhone(phone);

        List<ContactDto> contactDtos = contactService.findAll();
        assertNotNull(contactDtos);
        assertEquals(3, contactDtos.size());
    }

    @Test
    @DisplayName("Удаление контакта с неверным номером")
    void deleteContactWithWrongPhoneTest(){
        String wrongPhone = "800";

        assertThrows(NullPointerException.class, () -> {
            contactService.deleteContactByPhone(wrongPhone);
        });
    }
}