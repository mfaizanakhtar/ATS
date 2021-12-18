package com.ATS.Backend.service;

import com.ATS.Backend.model.DeliveryOrder;
import com.ATS.Backend.repository.DeliveryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryOrderService {
    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;

    public DeliveryOrder add(DeliveryOrder deliveryOrder, String username) {
        deliveryOrder.setCustomerUsername(username);

        int numberOfOrders = deliveryOrderRepository.findNumberOfOrdersByUsername(username);
        if(numberOfOrders>10) deliveryOrder.setCustomerType("VIP");
        else if(numberOfOrders>0) deliveryOrder.setCustomerType("LOYAL");
        else deliveryOrder.setCustomerType("NEW");

        int calculatedDeliveryTime= deliveryOrder.getDistanceFromDestination()*2;
        deliveryOrder.setExpectedDeliveryTime(LocalDateTime.now().plusMinutes(calculatedDeliveryTime));

        deliveryOrder.setTimeToReachDestination(LocalDateTime.now().plusMinutes(calculatedDeliveryTime+15));

        DeliveryOrder savedOrder = deliveryOrderRepository.save(deliveryOrder);
        return savedOrder;
    }
}
