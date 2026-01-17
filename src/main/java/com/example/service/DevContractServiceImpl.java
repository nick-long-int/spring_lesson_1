package com.example.service;

import com.example.dto.ContactDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("dev")
public class DevContractServiceImpl implements ContactService{
    @Override
    public List<ContactDto> findAll() {
        System.out.println("In dev profile");
        return List.of();
    }

    @Override
    public ContactDto findContactByPhone(String phone) {
        System.out.println("In dev profile");
        return null;
    }

    @Override
    public ContactDto updateContactByPhone(String phone, ContactDto contactDto) {
        System.out.println("In dev profile");
        return null;
    }

    @Override
    public ContactDto createContact(ContactDto contactDto) {
        System.out.println("In dev profile");
        return null;
    }

    @Override
    public void deleteContactByPhone(String phone) {
        System.out.println("In dev profile");
    }
}
