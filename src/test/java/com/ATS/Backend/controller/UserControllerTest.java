package com.ATS.Backend.controller;


import com.ATS.Backend.DTO.UserDTO;
import com.ATS.Backend.model.User;
import com.ATS.Backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
    UserService userDetailsService;

    @Test
    public void signupUser() throws Exception {


        UserDTO user = new UserDTO("testUer","user.213","ROLE_USER");

        given(userDetailsService.Adduser(Mockito.any(User.class))).willReturn(Mockito.any(User.class));

        ObjectMapper objectMapper = new ObjectMapper();
        String userReqBody =  objectMapper.writeValueAsString(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userReqBody))
                .andExpect(status().isOk());
    }
}
