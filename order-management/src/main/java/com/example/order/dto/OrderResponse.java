package com.example.order.dto;

import com.example.order.model.OrderLineItems;
import org.springframework.data.annotation.Id;

import java.util.List;

public class OrderResponse {
    @Id
    private String orderNumber;
    private List<OrderLineItems> orderLineItemsList;
    private String customerId;

    private String address;

    private String phoneNumber;

    public OrderResponse(String address, String phoneNumber) {

    }

    public OrderResponse(String orderNumber, List<OrderLineItems> orderLineItemsList, String customerId,String address, String phoneNumber) {
        this.orderNumber = orderNumber;
        this.orderLineItemsList = orderLineItemsList;
        this.customerId = customerId;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public OrderResponse() {

    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderLineItems> getOrderLineItemsList() {
        return orderLineItemsList;
    }

    public void setOrderLineItemsList(List<OrderLineItems> orderLineItemsList) {
        this.orderLineItemsList = orderLineItemsList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
