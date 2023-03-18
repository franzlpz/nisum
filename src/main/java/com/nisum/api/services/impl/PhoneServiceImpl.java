package com.nisum.api.services.impl;

import com.nisum.api.infrastructure.data.dtos.PhoneDto;
import com.nisum.api.infrastructure.data.models.Phone;
import com.nisum.api.infrastructure.data.repositories.PhoneRepository;
import com.nisum.api.services.PhoneService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhoneServiceImpl implements PhoneService {


	private final PhoneRepository phoneRepository;
	private final ModelMapper mapper;
	public PhoneServiceImpl(PhoneRepository phoneRepository, ModelMapper mapper) {
		this.phoneRepository = phoneRepository;
		this.mapper = mapper;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PhoneDto> findById(String id) {

		var phone = phoneRepository.findById(id);
		if (phone.isPresent()) {
			var dto = mapper.map(phone.get(), PhoneDto.class);
			return Optional.of(dto);
		}
		return Optional.empty();

	}

	@Override
	@Transactional
	public PhoneDto add(PhoneDto dto) {
		dto.setActive(true);
		phoneRepository.save(mapper.map(dto, Phone.class));
		return dto;
	}

	@Override
	@Transactional
	public PhoneDto update(PhoneDto dto) {
		phoneRepository.saveAndFlush(mapper.map(dto, Phone.class));
		return dto;
	}

	@Override
	@Transactional
	public void delete(PhoneDto dto) {
		var model = mapper.map( dto, Phone.class);
		phoneRepository.delete(model);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<PhoneDto> findAll() {
		return phoneRepository.findAll()
						.stream()
						.map(phone -> mapper.map(phone, PhoneDto.class)
						).collect(Collectors.toList());

	}

}
