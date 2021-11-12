package com.nisum.prueba.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Validaciones basicas para el proyecto.
 *
 * @author Franz Lopez
 * @version 1.1.0
 * @since 1.0
 */
public class Validations {


    /**
     * Patron para validar correos, se puede cambiar por mas especificos
     */
    public static final Pattern EMAIL_REGEX =
            Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    public static final Pattern PASSWORD_REGEX = Pattern.compile("(^[A-Z])([a-z]+)(\\d{2})");

    /**
     * Metodo de validacion de correo
     *
     * @param email Correo a validar estructura
     * @return retorna si es valido o no el correo
     */
    public static boolean validateEmail(String email) {
        Matcher matcher = EMAIL_REGEX.matcher(email);
        return matcher.find();
    }

    /**
     * Metodo de validacion de password
     *
     * @param password Password a validar
     * @return retorna si es valido o no el password
     */
    public static boolean validatePassword(String password) {
        Matcher matcher = PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }
}
