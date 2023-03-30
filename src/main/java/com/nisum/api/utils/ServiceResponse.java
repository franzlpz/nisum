package com.nisum.api.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Generic answers.
 *
 * @author Franz Lopez
 * @version 1.1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse  implements Serializable {
	private String mensaje;
	private Object data;

}
