package com.nisum.api.services;

import com.nisum.api.dtos.UserDto;
import com.nisum.api.utils.UserResponse;

public interface UserService extends GenericService<UserDto, String> {
	boolean existEmail(String email);
	UserDto findByEmail(String email);
	UserResponse add(UserDto dto);
	UserResponse update(UserDto dto);
}
