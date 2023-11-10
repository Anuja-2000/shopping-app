package com.example.tracking.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order_tracking")
public class OrderTrackingRequest {

    private String orderId;
    private String status;
    private String location;
    // Other tracking-related fields, getters, and setters


    public OrderTrackingRequest() {
    }

    public OrderTrackingRequest(String orderId, String status, String location) {

        this.orderId = orderId;
        this.status = status;
        this.location = location;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

