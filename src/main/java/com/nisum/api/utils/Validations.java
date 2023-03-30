package com.nisum.api.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * basics validations.
 *
 * @author Franz Lopez
 * @version 1.1.0
 * @since 1.0
 */

public class Validations {


	/**
	 * method to validate email
	 *
	 * @param email for validate structure
	 * @return return if is valid or not the email
	 */
	public static boolean validateEmail(String email) {

		String regex = Constants.EMAIL_REGEX;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}


	/**
	 * method to validate password
	 *
	 * @param password for validate structure
	 * @return return if is valid or not the password
	 */
	public static boolean validatePassword(String password) {
		String regex = Constants.PASSWORD_REGEX;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
}
