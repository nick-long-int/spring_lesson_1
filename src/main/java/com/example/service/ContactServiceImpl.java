package com.example.service;

import com.example.dto.ContactDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContactServiceImpl implements ContactService {

    private final Map<String, ContactDto> contacts = new HashMap<>();

    @Override
    public List<ContactDto> findAll() {
        return (ArrayList<ContactDto>) contacts.values();
    }

    @Override
    public ContactDto findContactByPhone(String phone) {
        return contacts.getOrDefault(phone, null);
    }

    @Override
    public ContactDto updateContactByPhone(String phone, ContactDto contactDto) {
        return contacts.put(phone, contactDto);
    }

    @Override
    public ContactDto createContact(ContactDto contactDto) {
        return contacts.put(contactDto.getPhone(), contactDto);
    }

    @Override
    public void deleteContactByPhone(String phone) {
        contacts.remove(phone);
    }
}
