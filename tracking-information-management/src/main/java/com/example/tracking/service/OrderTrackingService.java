package com.example.tracking.service;


import com.example.tracking.dto.OrderTrackingRequest;
import com.example.tracking.dto.OrderTrackingResponse;
import com.example.tracking.model.OrderTracking;
import com.example.tracking.repository.OrderTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
                orderTrackingRequest.getLocation()
        );
        orderTrackingRepository.save(orderTracking);
    }

    public OrderTrackingResponse mapToorderTrackingResponse(OrderTracking orderTracking){
        return new OrderTrackingResponse(
                orderTracking.getId(),
                orderTracking.getOrderId(),
                orderTracking.getStatus(),
                orderTracking.getLocation()
        );
    }

    public List<OrderTrackingResponse> getOrderTrackingByOrderId(String orderId) {
        List<OrderTracking> orderTracking = orderTrackingRepository.findByOrderId(orderId);
         return orderTracking.stream().map(this::mapToorderTrackingResponse).collect(Collectors.toList());
    }

    public OrderTrackingResponse updateStatus(String orderId, String status) {
        List<OrderTracking> orderTracking = orderTrackingRepository.findByOrderId(orderId);
        OrderTracking orderTracking1 = new OrderTracking(
        UUID.randomUUID().toString(),
        orderId,
        status,
        orderTracking.get(0).getLocation());
        orderTrackingRepository.save(orderTracking1);
        return mapToorderTrackingResponse(orderTracking1);

    }

}

