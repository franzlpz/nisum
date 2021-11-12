package com.nisum.prueba.mappers;

import com.nisum.prueba.dtos.PhoneDto;
import com.nisum.prueba.models.Phone;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    PhoneDto toPhoneDto(Phone model);
    Phone toPhone(PhoneDto dto);

    Collection<PhoneDto> toListPhoneDto(Collection<Phone> collection);
    Collection<Phone> toListPhone(Collection<PhoneDto> collection);
}
