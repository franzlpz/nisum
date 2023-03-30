package com.nisum.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;


public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final transient BindingResult result;
	private final transient HttpStatus httpStatus;
	public ServiceException(BindingResult result, HttpStatus httpStatus) {
		super();
		this.result = result;
		this.httpStatus = httpStatus;
	}

	public ServiceException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
		this.result = null;
	}

	public ServiceException(String message, BindingResult result, HttpStatus httpStatus) {
		super(message);
		this.result = result;
		this.httpStatus = httpStatus;
	}

	public BindingResult getResult() {
		return result;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
