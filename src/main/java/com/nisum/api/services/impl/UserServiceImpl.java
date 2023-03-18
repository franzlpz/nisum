package com.nisum.api.services.impl;

import com.nisum.api.infrastructure.data.dtos.UserDto;
import com.nisum.api.infrastructure.data.models.User;
import com.nisum.api.infrastructure.data.repositories.PhoneRepository;
import com.nisum.api.infrastructure.data.repositories.UserRepository;
import com.nisum.api.infrastructure.http.UserResponse;
import com.nisum.api.infrastructure.security.jwt.JwtInfoToken;
import com.nisum.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


	private final UserRepository userRepository;
	private final PhoneRepository phoneRepository;
	private final ModelMapper mapper;

	@Autowired
	private JwtInfoToken jwtInfoToken;

	public UserServiceImpl(UserRepository userRepository,
	                       PhoneRepository phoneRepository, ModelMapper mapper) {
		this.userRepository = userRepository;
		this.phoneRepository = phoneRepository;
		this.mapper = mapper;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UserDto> findById(String id) {
		var user = userRepository.findById(id);
		if(user.isPresent()){
			var dto = mapper.map(user.get(), UserDto.class);
			return Optional.of(dto);
		}

		return Optional.empty();

	}

	@Override
	@Transactional
	public UserResponse add(UserDto dto) {

		var user = mapper.map(dto, User.class);
		user.setCreated(new Date());
		user.setLastLogin(new Date());
		user.setActive(true);
		// generate JWT
		String jwt = jwtInfoToken.generateJwt(dto.getEmail());
		user.setToken(jwt);
		var responseUser = userRepository.save(user);
		var phones = mapper.map(dto, User.class).getPhones();
		var newPhones = phones
						.stream()
						.peek(temp -> {
							temp.setUser(responseUser);
							temp.setActive(true);
						}).collect(Collectors.toList());
		phoneRepository.saveAll(newPhones);
		return new UserResponse(responseUser.getId(),responseUser.getCreated(),
						responseUser.getModified(), responseUser.getLastLogin(),
						responseUser.getToken(),responseUser.isActive()) ;
	}

	@Override
	@Transactional
	public UserResponse update(UserDto dto) {

		Optional<User> user = userRepository.findById(dto.getId());
		user.ifPresent(value -> {
			value.setName(dto.getName());
			value.setEmail(dto.getEmail());
			value.setPassword(dto.getPassword());
			value.setModified(new Date());
			userRepository.saveAndFlush(value);
		});

		dto.getPhones().forEach(phone -> {
			phoneRepository.findById(phone.getId()).ifPresent(value -> {
				value.setNumber(phone.getNumber());
				value.setCityCode(phone.getCityCode());
				value.setCountryCode(phone.getCountryCode());
				phoneRepository.saveAndFlush(value);
			});
		});

		return new UserResponse(user.get().getId(),
						user.get().getCreated(),
						user.get().getModified(),
						user.get().getLastLogin(),
						UUID.randomUUID().toString(),
						user.get().isActive()) ;

	}
	@Override
	@Transactional
	public void delete(UserDto dto) {
		var model = mapper.map(dto, User.class);
		userRepository.delete(model);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<UserDto> findAll() {
		var users = userRepository.findAll();

		return users.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());

	}

	@Override
	public boolean existEmail(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

	@Override
	public UserDto findByEmail(String email) {
		var user = userRepository.findByEmail(email);
		return user.map(value -> mapper.map(value, UserDto.class)).orElse(null);
	}
}
