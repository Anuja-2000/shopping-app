package com.example.tracking.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order_tracking")
public class OrderTrackingResponse {
    @Id
    private String id;
    private String orderId;
    private String status;
    private String location;
    // Other tracking-related fields, getters, and setters


    public OrderTrackingResponse() {
    }

    public OrderTrackingResponse(String id, String orderId, String status, String location) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

