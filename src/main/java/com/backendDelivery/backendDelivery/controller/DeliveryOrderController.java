package com.backendDelivery.backendDelivery.controller;

import com.backendDelivery.backendDelivery.model.DeliveryOrder;
import com.backendDelivery.backendDelivery.service.DeliveryOrderService;
import org.apache.coyote.Response;
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
    public ResponseEntity<DeliveryOrder> createOrder(@RequestBody DeliveryOrder deliveryOrder,@AuthenticationPrincipal UserDetails userDetails){
        DeliveryOrder savedOrder = deliveryOrderService.add(deliveryOrder,userDetails.getUsername());
        return ResponseEntity.ok(savedOrder);

    }
}
