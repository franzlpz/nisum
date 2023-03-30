package com.nisum.api.dtos;

import com.nisum.api.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * User request.
 *
 * @author Franz Lopez
 * @version 1.1.0
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
	private String id;
	@NotEmpty(message = Constants.REQUIRED)
	private String name;
	@NotEmpty(message = Constants.REQUIRED)
	@Email(message = Constants.INVALID_EMAIL, regexp = Constants.EMAIL_REGEX)
	private String email;
	@NotEmpty(message = Constants.REQUIRED)
	@Size(min = 8, message = Constants.SISE_PASSWORD)
	@Pattern(regexp = Constants.PASSWORD_REGEX, message = Constants.INVALID_PASSWORD)
	private String password;
	private String token;
	@NotEmpty(message = Constants.REQUIRED)
	private Collection<PhoneDto> phones;
}
