package com.example.tracking.service;


import com.example.tracking.dto.OrderTrackingRequest;
import com.example.tracking.dto.OrderTrackingResponse;
import com.example.tracking.model.OrderTracking;
import com.example.tracking.repository.OrderTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
                new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()),
                orderTrackingRequest.getDescription()
        );
        orderTrackingRepository.save(orderTracking);
    }

    public OrderTrackingResponse mapToorderTrackingResponse(OrderTracking orderTracking) {
        return new OrderTrackingResponse(
                orderTracking.getId(),
                orderTracking.getOrderId(),
                orderTracking.getStatus(),
                orderTracking.getTimeStamp(),
                orderTracking.getDescription()
        );
    }

    public List<OrderTrackingResponse> getOrderTrackingByOrderId(String orderId) {
        List<OrderTracking> orderTracking = orderTrackingRepository.findByOrderId(orderId);
        return orderTracking.stream().map(this::mapToorderTrackingResponse).collect(Collectors.toList());
    }

    public OrderTrackingResponse updateStatus(OrderTrackingRequest orderTrackingRequest) {
//        List<OrderTracking> orderTracking = orderTrackingRepository.findByOrderId(orderTrackingRequest.getOrderId());
        OrderTracking orderTracking = new OrderTracking(
                UUID.randomUUID().toString(),
                orderTrackingRequest.getOrderId(),
                orderTrackingRequest.getStatus(),
                new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()),
                orderTrackingRequest.getDescription());
        orderTrackingRepository.save(orderTracking);
        return mapToorderTrackingResponse(orderTracking);

    }

}

