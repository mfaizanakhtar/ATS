package com.ATS.Backend.service;

import com.ATS.Backend.model.DeliveryOrder;
import com.ATS.Backend.model.OrderTickets;
import com.ATS.Backend.repository.DeliveryOrderRepository;
import com.ATS.Backend.repository.OrderTicketRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OrderTicketService {
    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;

    @Autowired
    OrderTicketRepository orderTicketRepository;

    public void generateTicketsBasedOnPriority(){
        List<DeliveryOrder> deliveryOrdersList =
                deliveryOrderRepository.findByTimeToReachDestinationLessThanAndDeliveryStatusNot(
                        LocalDateTime.now(),"delivered");

        deliveryOrdersList.forEach((order)->{
            int generatePriority=0;
            if(order.getCustomerType().equals("VIP")){
                generatePriority=generatePriority+10;
            }
            else if(order.getCustomerType().equals("LOYAL")){
                generatePriority=generatePriority+5;
            }

            Duration duration=Duration.between(order.getTimeToReachDestination(), LocalDateTime.now());
            long minutesDelay = duration.toMinutes();
            generatePriority = generatePriority + (int)minutesDelay;

            Optional<OrderTickets> findOrderTicket = Optional.ofNullable(orderTicketRepository.findByorderId(order.getOrderId()));
            if(findOrderTicket.isPresent()){
                findOrderTicket.get().setPriority(generatePriority);
                orderTicketRepository.save(findOrderTicket.get());
            }else{
                OrderTickets orderTicket =
                        new OrderTickets(order.getOrderId(),generatePriority,order.getCustomerUsername(),
                                order.getCustomerType(),
                                order.getTimeToReachDestination());

                orderTicketRepository.save(orderTicket);
            }
        });
    }

    public List<OrderTickets> getOrderTickets() {
//        return orderTicketRepository.findAll(Sort.by(Sort.Direction.DESC,"priority"));
        return orderTicketRepository.findByTicketStatusOrderByPriorityDesc("Open");
    }
}
