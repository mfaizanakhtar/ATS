package com.ATS.Backend.scheduler;

import com.ATS.Backend.service.OrderTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class schedulingtask {

    @Autowired
    OrderTicketService orderTicketService;

    @Scheduled(fixedRate = 2*60*1000) // 2 minutes
    public void createTickets(){
        log.info("generating tickets");
        orderTicketService.generateTicketsBasedOnPriority();
    }
}
