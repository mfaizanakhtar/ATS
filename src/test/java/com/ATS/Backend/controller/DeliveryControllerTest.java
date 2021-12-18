package com.ATS.Backend.controller;

import com.ATS.Backend.DTO.AuthRequest;
import com.ATS.Backend.model.DeliveryOrder;
import com.ATS.Backend.service.DeliveryOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DeliveryControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DeliveryOrderService deliveryOrderService;

    @Autowired
    TestRestTemplate restTemplate;

    String AuthRes;

    JSONObject AuthObj;

    @BeforeEach
    public void generateToken() throws Exception {

        AuthRequest authRequest = new AuthRequest("Daniel","user.123");

        ObjectMapper objectMapper =new ObjectMapper();

        String authJson = objectMapper.writeValueAsString(authRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authJson))
                .andDo(mvcResult->{
                     AuthRes= mvcResult.getResponse().getContentAsString();
                });
        AuthObj = new JSONObject(AuthRes);
    }

    @Test
    public void createOrder() throws Exception {
        DeliveryOrder deliveryOrder =
                new DeliveryOrder(1,"VIP","Daniel",
                        "pending", LocalDateTime.now(),20,LocalDateTime.now());

        given(deliveryOrderService.add(deliveryOrder, "Daniel")).willReturn(deliveryOrder);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestOrder = objectMapper.writeValueAsString(new DeliveryOrder());

        mockMvc.perform(MockMvcRequestBuilders.post("/deliveryOrder/add")
                        .header("Authorization","Bearer "+AuthObj.getString("jwt"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestOrder))
                .andExpect(status().isOk());
    }
}
