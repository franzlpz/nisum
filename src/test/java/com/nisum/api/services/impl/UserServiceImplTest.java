package com.nisum.api.services.impl;

import com.nisum.api.infrastructure.data.models.Phone;
import com.nisum.api.infrastructure.data.models.User;
import com.nisum.api.infrastructure.data.repositories.PhoneRepository;
import com.nisum.api.infrastructure.data.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private PhoneRepository phoneRepository;
	@Spy
	private ModelMapper mapper;

	@BeforeEach
	void setUp() {

	}

	@Test
	@DisplayName("Find user by id")
	void returnUserById() {
		// Arrange
		User user = getUser();
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		// Act
		var result = userService.findById(user.getId());
		// Assert
		assertTrue(result.isPresent());
	}

	@Test
	@DisplayName("Find all users")
	void returnAllUsers() {
		// Arrange
		User user = getUser();
		when(userRepository.findAll()).thenReturn(List.of(user));
		// Act
		var result = userService.findAll();
		// Assert
		assertEquals(1, result.size());
	}

	@Test
	@DisplayName("Find if exist email")
	void returnIfExistEmail() {
		// Arrange
		User user = getUser();
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
		// Act
		var result = userService.existEmail(user.getEmail());
		// Assert
		assertTrue(result);
	}

	private User getUser() {
		var user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setCreated(new Date());
		user.setLastLogin(new Date());
		user.setActive(true);
		user.setToken(UUID.randomUUID().toString());
		user.setName("Juan Rodriguez");
		user.setEmail("juan@rodriguez.org");
		user.setPassword("Hunter22");
		user.setPhones(List.of(getPhone()));
		return user;
	}

	private Phone getPhone() {
		var phone = new Phone();
		phone.setId(UUID.randomUUID().toString());
		phone.setNumber(123456789);
		phone.setCityCode("1");
		phone.setCountryCode("57");
		return phone;
	}

}