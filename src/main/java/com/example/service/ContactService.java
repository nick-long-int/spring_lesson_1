package com.example.service;

import com.example.dto.ContactDto;

import java.util.List;

public interface ContactService {
    List<ContactDto> findAll();
    ContactDto findContactByPhone(String phone);
    ContactDto updateContactByPhone(String phone, ContactDto contactDto);
    ContactDto createContact(ContactDto contactDto);
    void deleteContactByPhone(String phone);
}
