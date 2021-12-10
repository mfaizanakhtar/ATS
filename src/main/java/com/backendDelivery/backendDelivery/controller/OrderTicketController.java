package com.backendDelivery.backendDelivery.controller;

import com.backendDelivery.backendDelivery.model.OrderTickets;
import com.backendDelivery.backendDelivery.service.OrderTicketService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<OrderTickets> getOrderTickets(){
        return orderTicketService.getOrderTickets();
    }
}
