package com.nisum.api.exceptions;

import com.nisum.api.dtos.ErrorDto;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.validation.FieldError;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	protected ResponseEntity<ErrorDto> handleException(NoSuchElementException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return buildResponseEntity(httpStatus, exc);
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorDto> handleException(DuplicateKeyException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return buildResponseEntity(httpStatus, exc);
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorDto> handleException(IllegalArgumentException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return buildResponseEntity(httpStatus, exc);
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorDto> handleException(ServiceException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		List<String> errors = exc.getResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
						.collect(Collectors.toList());
		return buildResponseEntity(httpStatus, new RuntimeException("Data enviada es invalida"), errors);
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorDto> handleException(MethodArgumentTypeMismatchException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		return buildResponseEntity(httpStatus, new RuntimeException("Tipo de Argumento invalido"));
	}

	@ExceptionHandler
	protected ResponseEntity<ErrorDto> handleException(Exception exc) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return buildResponseEntity(httpStatus, new RuntimeException("Se presento un problema, reporte e intente luego."));
	}

	private ResponseEntity<ErrorDto> buildResponseEntity(HttpStatus httpStatus, Exception exc) {
		return buildResponseEntity(httpStatus, exc, null);
	}

	private ResponseEntity<ErrorDto> buildResponseEntity(HttpStatus httpStatus, Exception exc, List<String> errors) {
		ErrorDto error = new ErrorDto();
		error.setMessage("ERROR : " + exc.getMessage());
		error.setCode(httpStatus.value());
		error.setErrors(errors);
		return new ResponseEntity<>(error, httpStatus);

	}

}
