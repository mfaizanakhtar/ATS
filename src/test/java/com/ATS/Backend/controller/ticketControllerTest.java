package com.ATS.Backend.controller;

import com.ATS.Backend.DTO.AuthRequest;
import com.ATS.Backend.model.OrderTickets;
import com.ATS.Backend.service.OrderTicketService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ticketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderTicketService orderTicketService;

    @Autowired
    TestRestTemplate restTemplate;

    String AuthRes;

    JSONObject AuthObj;

    @BeforeEach
    public void generateToken() throws Exception {

        AuthRequest authRequest = new AuthRequest("Andy","support.123");

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
    public void getOrderTickets() throws Exception{
        List<OrderTickets> orderTicketList = new ArrayList<OrderTickets>();
        orderTicketList.add(new OrderTickets(12,20,"testUser", "VIP",LocalDateTime.now()));
        orderTicketList.add(new OrderTickets(12,20,"testUser","VIP", LocalDateTime.now()));

        given(orderTicketService.getOrderTickets()).willReturn(orderTicketList);

        mockMvc.perform(MockMvcRequestBuilders.get("/ticket/orders").header("Authorization","Bearer "+AuthObj.getString("jwt")))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()",greaterThan(0)));
    }
}
