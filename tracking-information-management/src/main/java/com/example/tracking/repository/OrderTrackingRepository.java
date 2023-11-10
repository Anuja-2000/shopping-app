package com.example.tracking.repository;

import com.example.tracking.model.OrderTracking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderTrackingRepository extends MongoRepository<OrderTracking, String> {

    List<OrderTracking> findByOrderId(String orderId);
}
