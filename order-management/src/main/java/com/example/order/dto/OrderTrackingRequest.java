package com.example.order.dto;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order_tracking")
public class OrderTrackingRequest {

    private String orderId;
    private String status;

    private String timeStamp;
    private String description;
    // Other tracking-related fields, getters, and setters


    public OrderTrackingRequest() {
    }

    public OrderTrackingRequest(String orderId, String status, String timeStamp, String description) {

        this.orderId = orderId;
        this.status = status;
        this.timeStamp = timeStamp;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

