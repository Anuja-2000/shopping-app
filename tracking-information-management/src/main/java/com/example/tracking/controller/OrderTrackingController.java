package com.example.tracking.controller;

import com.example.tracking.dto.OrderTrackingRequest;
import com.example.tracking.dto.OrderTrackingResponse;
import com.example.tracking.service.OrderTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-tracking")
public class OrderTrackingController {
    private final OrderTrackingService orderTrackingService;

    @Autowired
    public OrderTrackingController(OrderTrackingService orderTrackingService) {
        this.orderTrackingService = orderTrackingService;
    }

    @PostMapping
    public void createOrderTracking(@RequestBody OrderTrackingRequest orderTracking) {
        orderTrackingService.createOrderTracking(orderTracking);
    }

    @GetMapping("/{orderId}")
    public OrderTrackingResponse getOrderTrackingByOrderId(@PathVariable String orderId) {
        return orderTrackingService.getOrderTrackingByOrderId(orderId);
    }

    @PostMapping("/update")
    public OrderTrackingResponse updateState(@RequestBody OrderTrackingResponse updateState) {
        return orderTrackingService.updateStatus(updateState.getOrderId(), updateState.getStatus());
    }

}
