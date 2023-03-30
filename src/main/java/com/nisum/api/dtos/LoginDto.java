package com.nisum.api.dtos;

import com.nisum.api.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Login.
 *
 * @author Franz Lopez
 * @version 1.1.0
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto implements Serializable {
	@NotEmpty(message = Constants.REQUIRED)
	@Email(message = Constants.INVALID_EMAIL, regexp = Constants.EMAIL_REGEX)
	private String email;
}
