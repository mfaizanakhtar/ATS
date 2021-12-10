package com.backendDelivery.backendDelivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderTickets {
    @Id
    @GeneratedValue
    private int ticketId;
    private int orderId;
    private int priority;
    private String customerUsername;
    private LocalDateTime timeToReachDestination;


}
