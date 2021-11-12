package com.nisum.prueba.services;

import com.nisum.prueba.dtos.UserDto;
import com.nisum.prueba.dtos.UserResponse;

import java.util.Collection;
import java.util.Optional;


public interface IUserService {
    Optional<UserDto> findById(String id);
    UserResponse add(UserDto dto);
    UserResponse update(UserDto dto);
    void delete(UserDto dto);
    Collection<UserDto> findAll();
    boolean existEmail(String email);
}
