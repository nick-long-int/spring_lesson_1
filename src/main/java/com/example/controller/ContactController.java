package com.example.controller;

import com.example.dto.ContactDto;
import com.example.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ContactDto createContact(@RequestBody ContactDto contactDto) {
        return contactService.createContact(contactDto);
    }

    @GetMapping("/{phone}")
    public ContactDto findContactByPhone(@PathVariable String phone){
        return contactService.findContactByPhone(phone);
    }

    @GetMapping
    public List<ContactDto> findAllContacts(){
        return contactService.findAll();
    }

    @PutMapping("/{phone}")
    public ContactDto updateContact(@PathVariable String phone, @RequestBody ContactDto contactDto){
        return contactService.updateContactByPhone(phone, contactDto);
    }

    @DeleteMapping("/{phone}")
    public void deleteContact(@PathVariable String phone){
        contactService.deleteContactByPhone(phone);
    }

}
