package com.nisum.api.controllers;

import com.nisum.api.infrastructure.Utils.Constants;
import com.nisum.api.infrastructure.Utils.Validations;
import com.nisum.api.infrastructure.data.dtos.LoginDto;
import com.nisum.api.infrastructure.data.dtos.UserDto;
import com.nisum.api.infrastructure.http.ServiceResponse;
import com.nisum.api.infrastructure.http.UserResponse;
import com.nisum.api.services.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public UserController(UserService userService){
		this.userService = userService;
	}

	@PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> login(@RequestBody LoginDto dto) {

		try {
			var response = userService.findByEmail(dto.getEmail());

			if (response == null) {
				return new ResponseEntity<>(new ServiceResponse(Constants.MESSAGE_NOT_FOUND, null),
								HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(new ServiceResponse("", response), HttpStatus.OK);

		} catch (DataAccessException e) {
			return new ResponseEntity<>(new
							ServiceResponse( Objects.requireNonNull(e.getMessage())
							.concat(": ").concat(e.getMostSpecificCause().getMessage()), null),
							HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> list(){

		try {
			var response =  userService.findAll();
			if (response.isEmpty()) {
				return new ResponseEntity<>(new ServiceResponse("Elemento no encontrado",null), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(new ServiceResponse("", response), HttpStatus.OK);

		} catch (DataAccessException e) {
			return new ResponseEntity<>(new ServiceResponse(Objects.requireNonNull(e.getMessage())
							.concat(": ").concat(e.getMostSpecificCause().getMessage()), null),
							HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> getById(@PathVariable String id) {

		try {
			var response = userService.findById(id);

			if (response.isEmpty()) {
				return new ResponseEntity<>(new ServiceResponse("Elemento no encontrado", null), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(new ServiceResponse("", response), HttpStatus.OK);

		} catch (DataAccessException e) {
			return new ResponseEntity<>(new
							ServiceResponse( Objects.requireNonNull(e.getMessage())
							.concat(": ").concat(e.getMostSpecificCause().getMessage()), null),
							HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> create(@Valid @RequestBody UserDto dto, BindingResult result) {

		try {

			if (result.hasErrors()) {
				List<String> errors = result.getFieldErrors().stream()
								.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
								.collect(Collectors.toList());

				return new ResponseEntity<>(new ServiceResponse("Los datos enviados no son los correctos", errors),
								HttpStatus.BAD_REQUEST);
			}

			if(userService.existEmail(dto.getEmail())){
				return new ResponseEntity<>(new ServiceResponse("El correo ya registrado", null),
								HttpStatus.BAD_REQUEST);
			}
			if(!Validations.validateEmail(dto.getEmail())){
				return new ResponseEntity<>(new ServiceResponse("Formato de correo invalido", null),
								HttpStatus.BAD_REQUEST);
			}

			if(!Validations.validatePassword(dto.getPassword())){
				return new ResponseEntity<>(new ServiceResponse("El password no cumple con valores minimos", null),
								HttpStatus.BAD_REQUEST);
			}

			var response = userService.add(dto);
			return new ResponseEntity<>(new ServiceResponse("", response), HttpStatus.CREATED);

		} catch (DataAccessException e) {
			return new ResponseEntity<>(new
							ServiceResponse( Objects.requireNonNull(e.getMessage())
							.concat(": ").concat(e.getMostSpecificCause().getMessage()), null),
							HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> update(@Valid @RequestBody UserDto dto,
	                                              BindingResult result) {
		UserResponse response;

		try {
			if (result.hasErrors()) {
				List<String> errors = result.getFieldErrors().stream()
								.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
								.collect(Collectors.toList());

				return new ResponseEntity<>(new ServiceResponse("Los datos enviados no son los correctos", errors),
								HttpStatus.BAD_REQUEST);
			}

			if(userService.existEmail(dto.getEmail())){
				return new ResponseEntity<>(new ServiceResponse("El correo ya registrado", null),
								HttpStatus.BAD_REQUEST);
			}
			if(!Validations.validateEmail(dto.getEmail())){
				return new ResponseEntity<>(new ServiceResponse("Formato de correo invalido", null),
								HttpStatus.BAD_REQUEST);
			}

			if(!Validations.validatePassword(dto.getPassword())){
				return new ResponseEntity<>(new ServiceResponse("El password no cumple con valores minimos", null),
								HttpStatus.BAD_REQUEST);
			}

			response = userService.update(dto);

		} catch (DataAccessException e) {
			return new ResponseEntity<>(new
							ServiceResponse( Objects.requireNonNull(e.getMessage())
							.concat(": ").concat(e.getMostSpecificCause().getMessage()), null),
							HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(new ServiceResponse("", response), HttpStatus.OK);
	}
}
