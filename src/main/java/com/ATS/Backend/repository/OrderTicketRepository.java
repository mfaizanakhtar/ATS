package com.ATS.Backend.repository;

import com.ATS.Backend.model.OrderTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderTicketRepository extends JpaRepository<OrderTickets,Integer> {

    public OrderTickets findByorderId(int id);

    public List<OrderTickets> findByTicketStatusOrderByPriorityDesc(String ticketStatus);

}
