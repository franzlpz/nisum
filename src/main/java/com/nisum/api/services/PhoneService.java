package com.nisum.api.services;

import com.nisum.api.infrastructure.data.dtos.PhoneDto;

public interface PhoneService extends GenericService<PhoneDto, String> {
	PhoneDto add(PhoneDto dto);

	PhoneDto update(PhoneDto dto);
}
