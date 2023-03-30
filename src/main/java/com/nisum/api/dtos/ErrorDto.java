package com.nisum.api.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

	private int code;
	private String message;
	List<String> errors;
	public ErrorDto(String message) {
		this.message = message;
	}
}
