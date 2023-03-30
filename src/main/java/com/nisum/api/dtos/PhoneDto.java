package com.nisum.api.dtos;

import com.nisum.api.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Phone DTO.
 *
 * @author Franz Lopez
 * @version 1.1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto implements Serializable {
	private String id;
	@NotEmpty(message = Constants.REQUIRED)
	@Size(min = 7, message = Constants.SISE_PHONE)
	private int number;
	@NotEmpty(message = Constants.REQUIRED)
	private String cityCode;
	@NotEmpty(message = Constants.REQUIRED)
	private String countryCode;
	@NotEmpty(message = Constants.REQUIRED)
	private boolean isActive;

}
