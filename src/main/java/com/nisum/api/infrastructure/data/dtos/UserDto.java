package com.nisum.api.infrastructure.data.dtos;

import com.nisum.api.infrastructure.Utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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
	@NotNull(message = Constants.REQUIRED)
	private String name;
	@NotNull(message = Constants.REQUIRED)
	private String email;
	@NotNull(message = Constants.REQUIRED)
	private String password;
	private String token;
	@NotNull(message = Constants.REQUIRED)
	private Collection<PhoneDto> phones;
}
