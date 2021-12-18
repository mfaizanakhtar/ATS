package com.ATS.Backend.controller;

import com.ATS.Backend.model.OrderTickets;
import com.ATS.Backend.service.OrderTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/ticket")
public class OrderTicketController {
    @Autowired
    OrderTicketService orderTicketService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderTickets>> getOrderTickets(){
        return ResponseEntity.ok(orderTicketService.getOrderTickets());
    }
}
