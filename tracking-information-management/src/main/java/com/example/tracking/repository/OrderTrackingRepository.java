package com.example.tracking.repository;

import com.example.tracking.model.OrderTracking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderTrackingRepository extends MongoRepository<OrderTracking, String> {
    OrderTracking findByOrderId(String orderId);
}
