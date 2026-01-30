package com.example.mapper;

import com.example.dto.ContactDto;
import com.example.model.Contact;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactDto contactToContactDto(Contact contact);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateContact(ContactDto contactDto, @MappingTarget Contact contact);

    Contact contactDtoToContact(ContactDto contactDto);

}
