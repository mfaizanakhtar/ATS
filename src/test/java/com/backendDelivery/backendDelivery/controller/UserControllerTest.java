package com.backendDelivery.backendDelivery.controller;


import com.backendDelivery.backendDelivery.DTO.UserDTO;
import com.backendDelivery.backendDelivery.model.User;
import com.backendDelivery.backendDelivery.service.MyUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MyUserDetailsService userDetailsService;

    @Test
    public void signupUser() throws Exception {


        UserDTO user = new UserDTO("faizan123","faizanpassword","ROLES_USER");

        given(userDetailsService.Adduser(Mockito.any(User.class))).willReturn(Mockito.any(User.class));

        ObjectMapper objectMapper = new ObjectMapper();
        String userReqBody =  objectMapper.writeValueAsString(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userReqBody))
                .andExpect(status().isOk());
    }
}
