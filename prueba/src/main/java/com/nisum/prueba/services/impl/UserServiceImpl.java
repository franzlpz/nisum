package com.nisum.prueba.services.impl;

import com.nisum.prueba.dtos.PhoneDto;
import com.nisum.prueba.dtos.UserDto;
import com.nisum.prueba.dtos.UserResponse;
import com.nisum.prueba.mappers.PhoneMapper;
import com.nisum.prueba.mappers.UserMapper;
import com.nisum.prueba.models.User;
import com.nisum.prueba.repositories.IPhoneRepository;
import com.nisum.prueba.repositories.IUserRepository;
import com.nisum.prueba.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPhoneRepository phoneRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PhoneMapper phoneMapper;

//    @Autowired
//    private BCryptPasswordEncoder bcrypt;

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findById(String id) {
        var user = userRepository.findById(id);
        if(user.isPresent()){
            var phones = phoneRepository.findByUser(user.get());
            var dto = userMapper.toUserDto(user.get());
            dto.setPhones(phoneMapper.toListPhoneDto(phones));
            return Optional.of(dto);
        }

        return Optional.empty();

    }

    @Override
    @Transactional
    public UserResponse add(UserDto dto) {

        var user = userMapper.toUser(dto);
        user.setCreated(new Date());
        user.setLastLogin(new Date());
        user.setActive(true);
        var responseUser = userRepository.save(user);
        var phones = phoneMapper.toListPhone(dto.getPhones());
        var newPhones = phones
                .stream()
                .map(temp -> {
            temp.setUser(responseUser);
            temp.setActive(true);
            return temp;
        }).collect(Collectors.toList());
        phoneRepository.saveAll(newPhones);
        return new UserResponse(responseUser.getId(),responseUser.getCreated(),
                responseUser.getModified(), responseUser.getLastLogin(),UUID.randomUUID().toString(),responseUser.isActive()) ;
    }

    @Override
    @Transactional
    public UserResponse update(UserDto dto) {
        var user = userMapper.toUser(dto);
        var response =userRepository.saveAndFlush(user);
        return new UserResponse(response.getId(),response.getCreated(),
                response.getModified(), response.getLastLogin(),UUID.randomUUID().toString(),response.isActive()) ;
    }
    @Override
    @Transactional
    public void delete(UserDto dto) {
        var model = userMapper.toUser(dto);
        userRepository.delete(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<UserDto> findAll() {
        var users = userRepository.findAll();

        return users.stream().map(temp -> {
            var dto = new UserDto();
            dto.setEmail(temp.getEmail());
            dto.setName(temp.getName());
            dto.setPassword(temp.getPassword());
            dto.setPhones(phoneMapper.toListPhoneDto(phoneRepository.findByUser(temp)));
            return dto;
        }).collect(Collectors.toList());

    }

    @Override
    public boolean existEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
