package com.backendDelivery.backendDelivery.service;

import com.backendDelivery.backendDelivery.model.DeliveryOrder;
import com.backendDelivery.backendDelivery.model.OrderTickets;
import com.backendDelivery.backendDelivery.repository.DeliveryOrderRepository;
import com.backendDelivery.backendDelivery.repository.OrderTicketRepository;
import org.apache.tomcat.jni.Local;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderTicketService {
    private static final Logger log = LoggerFactory.getLogger(OrderTicketService.class);
    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;

    @Autowired
    OrderTicketRepository orderTicketRepository;

    public void generateTicketsBasedOnPriority(){
        List<DeliveryOrder> deliveryOrdersList =
                deliveryOrderRepository.findByexpectedDeliveryTimeLessThan(LocalDateTime.now());

        deliveryOrdersList.forEach((order)->{
            int generatePriority=0;
            if(order.getCustomerType()=="VIP"){
                generatePriority=generatePriority+10;
            }
            else if(order.getCustomerType()=="LOYAL"){
                generatePriority=generatePriority+5;
            }
            Duration duration=Duration.between(order.getTimeToReachDestination(), LocalDateTime.now());
            long minutesDelay = duration.toMinutes();
            generatePriority = generatePriority + (int)minutesDelay;

            Optional<OrderTickets> findOrderTicket = Optional.ofNullable(orderTicketRepository.findByorderId(order.getId()));
            if(findOrderTicket.isPresent()){
                findOrderTicket.get().setPriority(generatePriority);
                orderTicketRepository.save(findOrderTicket.get());
            }else{
                OrderTickets orderTicket = new OrderTickets();
                orderTicket.setOrderId(order.getId());
                orderTicket.setPriority(generatePriority);
                orderTicket.setCustomerUsername(order.getCustomerUsername());
                orderTicket.setTimeToReachDestination(order.getTimeToReachDestination());

                orderTicketRepository.save(orderTicket);
            }
        });
    }

    public List<OrderTickets> getOrderTickets() {
        return orderTicketRepository.findAll(Sort.by(Sort.Direction.DESC,"priority"));
    }
}
