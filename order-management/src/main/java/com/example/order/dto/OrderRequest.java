package com.example.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderRequest {

    private String customerId;
    private List<OrderLineItemsDto> orderLineItemsDtoList;

    public OrderRequest(@JsonProperty("customerId") String customerId, @JsonProperty("orderLineItemsDtoList") List<OrderLineItemsDto> orderLineItemsDtoList) {
        this.customerId = customerId;
        this.orderLineItemsDtoList = orderLineItemsDtoList;
    }

    public List<OrderLineItemsDto> getOrderLineItemsDtoList() {
        return orderLineItemsDtoList;
    }

    public void setOrderLineItemsDtoList(List<OrderLineItemsDto> orderLineItemsDtoList) {
        this.orderLineItemsDtoList = orderLineItemsDtoList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
