package com.ATS.Backend.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class OrderTickets {
    @Id
    @GeneratedValue
    private int ticketId;
    @NonNull
    private int orderId;
    @NonNull
    private int priority;
    @NonNull
    private String customerUsername;
    @NonNull
    private String customerType;
    @NonNull
    private LocalDateTime timeToReachDestination;
    private String ticketStatus="Open";


}
