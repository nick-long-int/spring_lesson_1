package com.example.service;

import com.example.dto.ContactDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Profile("prod")
public class ContactServiceImpl implements ContactService {

    private final Map<String, ContactDto> contacts = new HashMap<>();

    @Override
    public List<ContactDto> findAll() {
        return contacts.values().stream().toList();
    }

    @Override
    @Cacheable(value = "contacts", key = "#phone")
    public ContactDto findContactByPhone(String phone) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return contacts.getOrDefault(phone, null);
    }

    @Override
    @CachePut(value = "contacts", key = "#phone")
    public ContactDto updateContactByPhone(String phone, ContactDto contactDto) {
        return contacts.put(phone, contactDto);
    }

    @Override
    @CachePut(value = "contacts", key = "#contactDto.phone")
    public ContactDto createContact(ContactDto contactDto) {
        return contacts.put(contactDto.getPhone(), contactDto);
    }

    @Override
    @CacheEvict(value = "products", key = "#phone")
    public void deleteContactByPhone(String phone) {
        contacts.remove(phone);
    }
}
