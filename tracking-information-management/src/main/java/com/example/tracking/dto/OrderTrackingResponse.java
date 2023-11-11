package com.example.tracking.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order_tracking")
public class OrderTrackingResponse {
    @Id
    private String id;
    private String orderId;
    private String status;
    private String timeStamp;
    private String description;
    // Other tracking-related fields, getters, and setters


    public OrderTrackingResponse() {
    }

    public OrderTrackingResponse(String id, String orderId, String status, String timeStamp, String description) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.timeStamp = timeStamp;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}

