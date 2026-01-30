package com.example.service;

import com.example.dto.ContactDto;
import com.example.mapper.ContactMapper;
import com.example.model.Contact;
import com.example.repo.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    @Transactional(
        readOnly = true,
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRED
    )
    public List<ContactDto> findAll() {
        return contactRepository.findAll()
            .stream()
            .map(contactMapper::contactToContactDto)
            .toList();
    }

    @Override
    @Transactional(
        readOnly = true,
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRED
    )
    public ContactDto findContactByPhone(String phone) {

        return contactMapper.contactToContactDto(
        contactRepository.findContactByPhone(phone)
            .orElseThrow(() ->
                new NullPointerException("Contact with phone " + phone + " not found")));
    }

    @Override
    @Transactional(
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRED
    )
    public ContactDto updateContactByPhone(String phone, ContactDto contactDto) {

        Contact contact = contactRepository.findContactByPhone(phone)
            .orElseThrow(() -> new NullPointerException("Contact with phone " + phone + " not found"));

        contactMapper.updateContact(contactDto, contact);

        return contactMapper.contactToContactDto(contactRepository.save(contact));
    }

    @Override
    @Transactional(
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRED
    )
    public ContactDto createContact(ContactDto contactDto) {
        return contactMapper.contactToContactDto(
          contactRepository.save(contactMapper.contactDtoToContact(contactDto))
        );
    }

    @Override
    @Transactional(
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRED
    )
    public void deleteContactByPhone(String phone) {
        Contact contact = contactRepository.findContactByPhone(phone)
            .orElseThrow(() -> new NullPointerException("Contact with phone " + phone + " not found"));
        contactRepository.delete(contact);
    }
}
