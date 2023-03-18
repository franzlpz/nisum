package com.nisum.api.services;

import com.nisum.api.infrastructure.data.dtos.UserDto;
import com.nisum.api.infrastructure.http.UserResponse;

public interface UserService extends GenericService<UserDto, String> {
	boolean existEmail(String email);
	UserDto findByEmail(String email);
	UserResponse add(UserDto dto);
	UserResponse update(UserDto dto);
}
