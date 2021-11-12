package com.nisum.prueba.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.prueba.dtos.PhoneDto;
import com.nisum.prueba.dtos.UserDto;
import com.nisum.prueba.dtos.UserResponse;
import com.nisum.prueba.services.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    IUserService userService;

    @Test
    void list() throws Exception{

        var users = getUsers();
        Mockito.when(userService.findAll()).thenReturn(users);

        mvc.perform(MockMvcRequestBuilders
                .get( "/users")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception{

        var user = getUser();
        Mockito.when(userService.findById("40289fcb7d15bfea017d15c00e6c0000")).thenReturn(java.util.Optional.of(user));

        mvc.perform(MockMvcRequestBuilders
                .get( "/users/40289fcb7d15bfea017d15c00e6c0000")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception{

        var user = getUser();
        Mockito.when(userService.add(user)).thenReturn(getUserResponse());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(user));

        mvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception{

        var user = getUser();
        Mockito.when(userService.update(user)).thenReturn(getUserResponse());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(user));

        mvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    private List<UserDto> getUsers(){

        var list = new ArrayList<UserDto>();

        var user1 = new UserDto("Juan2 Rodriguez","juan2@rodriguez.te.org","Hunter33", getPhones());
        var user2 = new UserDto("Juan1 Rodriguez","juan1@rodriguez.org","Hunter33", getPhones());
        list.add(user1);
        list.add(user2);

        return list;
    }

    private UserDto getUser(){
        return new UserDto("Juan2 Rodriguez","juan2@rodriguez.te.org","Hunter33", getPhones());
    }

    private UserResponse getUserResponse(){
        var response = new UserResponse();
        response.setId("40289fcb7d15bfea017d15c00e6c0000");
        response.setCreated(new Date());
        response.setActive(true);
        response.setLastLogin(new Date());
        response.setModified(new Date());
        response.setToken(UUID.randomUUID().toString());
        return response;
    }

    private List<PhoneDto> getPhones(){
        var listPhones = new ArrayList<PhoneDto>();
        var phone1 = new PhoneDto(UUID.randomUUID().toString(),1234567,"1","57",true);
        var phone12 = new PhoneDto(UUID.randomUUID().toString(),1234567,"1","57",true);
        listPhones.add(phone1);
        listPhones.add(phone12);
        return listPhones;
    }
}
