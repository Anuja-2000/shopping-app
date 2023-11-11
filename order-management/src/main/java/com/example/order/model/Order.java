package com.example.order.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "order")
public class Order {
    @Id
    private String orderNumber;
    private List<OrderLineItems> orderLineItemsList;

    private String customerId;

    private String address;

    private String phoneNumber;


    public Order(String orderNumber, List<OrderLineItems> orderLineItemsList, String customerId, String address, String phoneNumber) {
        this.orderNumber = orderNumber;
        this.orderLineItemsList = orderLineItemsList;
        this.customerId = customerId;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Order() {

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
