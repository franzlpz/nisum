package com.nisum.api.infrastructure.data.dtos;

import com.nisum.api.infrastructure.Utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	@NotNull(message = Constants.REQUIRED)
	private String email;
}
