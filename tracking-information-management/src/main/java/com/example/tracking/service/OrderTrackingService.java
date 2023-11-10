package com.example.tracking.service;


import com.example.tracking.dto.OrderTrackingRequest;
import com.example.tracking.dto.OrderTrackingResponse;
import com.example.tracking.model.OrderTracking;
import com.example.tracking.repository.OrderTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderTrackingService {
    private final OrderTrackingRepository orderTrackingRepository;

    @Autowired
    public OrderTrackingService(OrderTrackingRepository orderTrackingRepository) {
        this.orderTrackingRepository = orderTrackingRepository;
    }

    public void createOrderTracking(OrderTrackingRequest orderTrackingRequest) {
        UUID id = UUID.randomUUID();
        OrderTracking orderTracking = new OrderTracking(
                id.toString(),
                orderTrackingRequest.getOrderId(),
                orderTrackingRequest.getStatus(),
                orderTrackingRequest.getStatus()
        );
        orderTrackingRepository.save(orderTracking);
    }

    public OrderTrackingResponse mapToorderTrackingResponse(OrderTracking orderTracking){
        return new OrderTrackingResponse(
                orderTracking.getId(),
                orderTracking.getOrderId(),
                orderTracking.getStatus(),
                orderTracking.getStatus()
        );
    }

    public OrderTrackingResponse getOrderTrackingByOrderId(String orderId) {
        OrderTracking orderTracking = orderTrackingRepository.findByOrderId(orderId);
        return mapToorderTrackingResponse(orderTracking);
    }

    public OrderTrackingResponse updateStatus(String orderId, String status) {
        OrderTracking orderTracking = orderTrackingRepository.findByOrderId(orderId);
        orderTracking.setStatus(status);
        orderTrackingRepository.save(orderTracking);
        return mapToorderTrackingResponse(orderTracking);

    }

    // Implement other business logic as needed
}

