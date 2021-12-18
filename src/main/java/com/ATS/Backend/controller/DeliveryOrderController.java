package com.ATS.Backend.controller;

import com.ATS.Backend.model.DeliveryOrder;
import com.ATS.Backend.service.DeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deliveryOrder")
public class DeliveryOrderController {
    @Autowired
    DeliveryOrderService deliveryOrderService;

    @PostMapping("/add")
    public ResponseEntity<DeliveryOrder> createOrder(@RequestBody DeliveryOrder deliveryOrder, @AuthenticationPrincipal UserDetails userDetails){
        DeliveryOrder savedOrder = deliveryOrderService.add(deliveryOrder,userDetails.getUsername());
        return ResponseEntity.ok(savedOrder);

    }
}
