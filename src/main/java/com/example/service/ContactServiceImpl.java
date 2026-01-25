package com.example.service;

import com.example.dto.ContactDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final Map<String, ContactDto> contacts;
    @Override
    public List<ContactDto> findAll() {
        return contacts.values().stream().toList();
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
