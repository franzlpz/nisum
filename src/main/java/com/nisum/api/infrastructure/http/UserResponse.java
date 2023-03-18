package com.nisum.api.infrastructure.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * User response.
 *
 * @author Franz Lopez
 * @version 1.1.0
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {

	private String id;
	private Date created;
	private Date modified;
	private Date lastLogin;
	private String token;
	private boolean isActive;

}
