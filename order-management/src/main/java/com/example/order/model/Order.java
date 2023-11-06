package com.example.order.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "order")
public class Order {
    @Id
    private String orderNumber;
    private List<OrderLineItems> orderLineItemsList;

    public Order(String orderNumber, List<OrderLineItems> orderLineItemsList) {
        this.orderNumber = orderNumber;
        this.orderLineItemsList = orderLineItemsList;
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
}
