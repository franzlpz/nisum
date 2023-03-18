package com.nisum.api.infrastructure.data.dtos;

import com.nisum.api.infrastructure.Utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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
	@NotNull(message = Constants.REQUIRED)
	@Size(min = 7, message = Constants.SISE_PHONE)
	private int number;
	@NotNull(message = Constants.REQUIRED)
	private String cityCode;
	@NotNull(message = Constants.REQUIRED)
	private String countryCode;
	@NotNull(message = Constants.REQUIRED)
	private boolean isActive;

}
