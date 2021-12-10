package com.backendDelivery.backendDelivery.repository;

import com.backendDelivery.backendDelivery.model.OrderTickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTicketRepository extends JpaRepository<OrderTickets,Integer> {

}
