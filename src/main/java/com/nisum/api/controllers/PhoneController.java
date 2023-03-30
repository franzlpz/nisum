package com.nisum.api.controllers;

import com.nisum.api.exceptions.ServiceException;
import com.nisum.api.utils.Constants;
import com.nisum.api.utils.ServiceResponse;
import com.nisum.api.services.PhoneService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
			var phones =  phoneService.findAll();
			return new ResponseEntity<>(new ServiceResponse("", phones), HttpStatus.OK);
	}

}
