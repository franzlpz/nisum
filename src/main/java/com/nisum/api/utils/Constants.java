package com.nisum.api.utils;

public class Constants {
	public static final String REQUIRED = "Elemento obligatorio";
	public static final String SISE_PHONE = "Debe tener como minimo 7 numeros";
	public static final String MESSAGE_NOT_FOUND = "No se encontro el elemento";

	public static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
					"(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
					"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f" +
					"]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9]" +
					"(?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:" +
					"(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2" +
					"(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:" +
					"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f" +
					"]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	public static final String PASSWORD_REGEX = "(^[A-Z])([a-z]+)(\\d{2})";
	public static final String INVALID_EMAIL = "Email invalido";
	public static final String SISE_PASSWORD = "Debe tener como minimo 8 caracteres";
	public static final String INVALID_PASSWORD = "Password invalido" ;
	public static final String REQUIRED_FIELDS = "Campos obligatorios";
}
