package com.example.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Контроллер контактов")
@ExtendWith(MockitoExtension.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String content;

    @BeforeEach
    void setUp() {
        content = """
            {
                "lastName": "Test",
                "firstName": "Test",
                "middleName": "Test",
                "phone": "123456789"
            }
            """;
    }

    @Test
    @DisplayName("Создать контакт")
    void testCreateContact() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/api/v1/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @DisplayName("Найти контакт по номеру")
    void testFindByPhone() throws Exception {

        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/api/v1/contacts/123456789")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Обновить контакт")
    void testUpdateContact() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders
                .put("/api/v1/contacts/123456789")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Удалить контакт")
    void testDeleteContact() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders
                .delete("/api/v1/contacts/123456789")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Контакт не найден")
    void testFindByPhoneWhenContactNotFound() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/api/v1/contacts/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.content().string(""));
    }
}