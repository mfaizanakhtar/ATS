package com.backendDelivery.backendDelivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrder {
    @Column(name="delivery_id")
    @GeneratedValue
    @Id
    private int id;

    @Column(name="customer_type")
    private String customerType;

    @Column(name="customer_username")
    private String customerUsername;

    @Column(name="delivery_status")
    private String deliveryStatus;

    @Column(name="expected_delivery_time")
    private LocalDateTime expectedDeliveryTime;

    @Column(name="current_distance_from_destination_in_meters")
    private int distanceFromDestination;

    @Column(name="time_to_reach_destination")
    private LocalDateTime timeToReachDestination;
}
