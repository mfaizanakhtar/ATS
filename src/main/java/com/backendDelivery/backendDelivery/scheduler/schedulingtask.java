package com.backendDelivery.backendDelivery.scheduler;

import com.backendDelivery.backendDelivery.service.OrderTicketService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class schedulingtask {

    @Autowired
    OrderTicketService orderTicketService;

    @Scheduled(fixedRate = 1000*60)
    public void createTickets(){
        log.info("Inside function");
        orderTicketService.generateTicketsBasedOnPriority();
    }
}
