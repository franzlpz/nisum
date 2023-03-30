package com.nisum.api.controllers;

import com.nisum.api.dtos.LoginDto;
import com.nisum.api.dtos.UserDto;
import com.nisum.api.exceptions.ServiceException;
import com.nisum.api.services.UserService;
import com.nisum.api.utils.Constants;
import com.nisum.api.utils.ServiceResponse;
import com.nisum.api.utils.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * User controller.
 *
 * @author Franz Lopez
 * @version 1.1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/users")
public class UserController {


	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> login(@RequestBody LoginDto dto) {

		UserDto response = userService.findByEmail(dto.getEmail());

		if (response == null) {
			throw new ServiceException(Constants.MESSAGE_NOT_FOUND,
							HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ServiceResponse("", response),
						HttpStatus.OK);

	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> getAll() {

		var response = userService.findAll();
		if (response.isEmpty()) {
			throw new ServiceException(Constants.MESSAGE_NOT_FOUND,
							HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ServiceResponse("", response),
						HttpStatus.OK);

	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> getById(@PathVariable String id) {

		var response = userService.findById(id);

		if (response.isEmpty()) {
			throw new ServiceException(Constants.MESSAGE_NOT_FOUND,
							HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ServiceResponse("", response),
						HttpStatus.OK);

	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> create(@Valid @RequestBody UserDto dto, BindingResult result) {


		if (result.hasErrors()) {
			throw new ServiceException(Constants.REQUIRED_FIELDS, result,
							HttpStatus.BAD_REQUEST);
		}

		if (userService.existEmail(dto.getEmail())) {
			throw new ServiceException("El correo ya registrado",
							HttpStatus.BAD_REQUEST);
		}

		var response = userService.add(dto);
		return new ResponseEntity<>(new ServiceResponse("", response),
						HttpStatus.CREATED);


	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> update(@Valid @RequestBody UserDto dto, BindingResult result) {

		if (result.hasErrors()) {
			throw new ServiceException(Constants.REQUIRED_FIELDS, result,
							HttpStatus.BAD_REQUEST);
		}

		if (userService.existEmail(dto.getEmail())) {
			throw new ServiceException("El correo ya registrado",
							HttpStatus.BAD_REQUEST);
		}

		UserResponse response = userService.update(dto);


		return new ResponseEntity<>(new ServiceResponse("", response),
						HttpStatus.OK);
	}
}
