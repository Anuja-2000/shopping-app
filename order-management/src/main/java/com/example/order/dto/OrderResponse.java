package com.example.order.dto;

import com.example.order.model.OrderLineItems;
import org.springframework.data.annotation.Id;

import java.util.List;

public class OrderResponse {
    @Id
    private String orderNumber;
    private List<OrderLineItems> orderLineItemsList;

    public OrderResponse(String orderNumber, List<OrderLineItems> orderLineItemsList) {
        this.orderNumber = orderNumber;
        this.orderLineItemsList = orderLineItemsList;
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
}
