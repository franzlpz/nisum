package com.nisum.api.infrastructure.Utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ValidationsTest {


	@Test
	void returnValidPassword() {
		// Arrange
		String password = "Juanperez01";
		// Act
		boolean result = Validations.validatePassword(password);
		// Assert
		assertTrue(result);
	}

	@Test
	void returnInvalidPassword() {
		// Arrange
		String password = "12345678";
		// Act
		boolean result = Validations.validatePassword(password);
		// Assert
		assertFalse(result);
	}

	@Test
	void returnValidEmail() {
		// Arrange
		String email = "usuario@correo.com";
		// Act
		boolean result = Validations.validateEmail(email);
		// Assert
		assertTrue(result);
	}

	@Test
	void returnInvalidEmail() {
		// Arrange
		String email = "dsd@@corresso.cossm";
		// Act
		boolean result = Validations.validateEmail(email);
		// Assert
		assertFalse(result);
	}

}