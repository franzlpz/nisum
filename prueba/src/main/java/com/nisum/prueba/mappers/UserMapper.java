package com.nisum.prueba.mappers;

import com.nisum.prueba.dtos.UserDto;
import com.nisum.prueba.models.User;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User model);
    User toUser(UserDto dto);

    Collection<UserDto> toListUserDto(Collection<User>  collection);
    Collection<User> toListUser(Collection<UserDto> collection);


}
