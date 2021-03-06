package com.nisum.prueba.dtos;

import com.nisum.prueba.helpers.Constants;
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
    private static final long serialVersionUID = 1L;

    @NotNull(message = Constants.REQUIRED)
    private String name;
    @NotNull(message = Constants.REQUIRED)
    private String email;
    @NotNull(message = Constants.REQUIRED)
    private String password;
    @NotNull(message = Constants.REQUIRED)
    private Collection<PhoneDto> phones;
}
