package com.nisum.api.controllers;

import com.nisum.api.infrastructure.data.dtos.UserDto;
import com.nisum.api.infrastructure.http.ServiceResponse;
import com.nisum.api.services.PhoneService;
import com.nisum.api.services.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping("/api/phones")
public class PhoneController {
	private final PhoneService phoneService;

	public PhoneController(PhoneService phoneService){
		this.phoneService = phoneService;
	}

	@GetMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> list(){

		try {
			var phones =  phoneService.findAll();

			if (phones.isEmpty()) {
				return new ResponseEntity<>(new ServiceResponse("Elemento no encontrado",null), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(new ServiceResponse("", phones), HttpStatus.OK);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(new ServiceResponse(Objects.requireNonNull(e.getMessage())
							.concat(": ").concat(e.getMostSpecificCause().getMessage()), null),
							HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
