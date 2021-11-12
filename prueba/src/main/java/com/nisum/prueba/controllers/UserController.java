package com.nisum.prueba.controllers;


import com.nisum.prueba.dtos.ServiceResponse;
import com.nisum.prueba.dtos.UserDto;
import com.nisum.prueba.dtos.UserResponse;
import com.nisum.prueba.helpers.Validations;
import com.nisum.prueba.services.IUserService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * User controller.
 *
 * @author Franz Lopez
 * @version 1.1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE, consumes =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse> list(){
        Collection<UserDto> users;
        try {
            users =  userService.findAll();
        } catch (DataAccessException e) {
            return new ResponseEntity<>(new ServiceResponse(Objects.requireNonNull(e.getMessage())
                            .concat(": ").concat(e.getMostSpecificCause().getMessage()), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (users.isEmpty()) {
            return new ResponseEntity<>(new ServiceResponse("Elemento no encontrado",null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ServiceResponse("", users), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse> getById(@PathVariable String id) {
        Optional<UserDto> user;
        try {
            user = userService.findById(id);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(new
                    ServiceResponse( Objects.requireNonNull(e.getMessage())
                            .concat(": ").concat(e.getMostSpecificCause().getMessage()), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (user.isEmpty()) {
            return new ResponseEntity<>(new ServiceResponse("Elemento no encontrado", null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ServiceResponse("", user), HttpStatus.OK);
    }

    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE, consumes =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse> create(@Valid @RequestBody UserDto dto, BindingResult result) {
        UserResponse response;

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(new ServiceResponse("Los datos enviados no son los correctos", errors),
                    HttpStatus.BAD_REQUEST);
        }
        if(userService.existEmail(dto.getEmail())){
            return new ResponseEntity<>(new ServiceResponse("El correo ya registrado", null),
                    HttpStatus.BAD_REQUEST);
        }
        if(!Validations.validateEmail(dto.getEmail())){
            return new ResponseEntity<>(new ServiceResponse("Formato de correo invalido", null),
                    HttpStatus.BAD_REQUEST);
        }

        if(!Validations.validatePassword(dto.getPassword())){
            return new ResponseEntity<>(new ServiceResponse("El password no cumple con valores minimos", null),
                    HttpStatus.BAD_REQUEST);
        }

        try {
            response = userService.add(dto);

        } catch (DataAccessException e) {
            return new ResponseEntity<>(new
                    ServiceResponse( Objects.requireNonNull(e.getMessage())
                    .concat(": ").concat(e.getMostSpecificCause().getMessage()), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ServiceResponse("", response), HttpStatus.CREATED);
    }

    @PutMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE, consumes =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse> update(@Valid @RequestBody UserDto dto) {
        UserResponse response;

        if (dto == null) {
            return new ResponseEntity<>(new ServiceResponse("No tiene valores ", null), HttpStatus.NOT_FOUND);
        }

        try {

            response = userService.update(dto);

        } catch (DataAccessException e) {
            return new ResponseEntity<>(new
                    ServiceResponse( Objects.requireNonNull(e.getMessage())
                    .concat(": ").concat(e.getMostSpecificCause().getMessage()), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new ServiceResponse("", response), HttpStatus.OK);
    }
}
