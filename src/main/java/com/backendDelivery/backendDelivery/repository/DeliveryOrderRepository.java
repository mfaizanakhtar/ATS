package com.backendDelivery.backendDelivery.repository;

import com.backendDelivery.backendDelivery.model.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder,Integer> {
    @Query(value="Select COUNT(*) from DeliveryOrder where customer_username=:username")
    public int findNumberOfOrdersByUsername(String username);

    public List<DeliveryOrder> findByexpectedDeliveryTimeLessThan(LocalDateTime localDateTime);

}
