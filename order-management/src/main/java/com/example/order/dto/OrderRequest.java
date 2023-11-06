package com.example.order.dto;

import com.example.order.model.OrderLineItems;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsDtoList;

    public OrderRequest(@JsonProperty("orderLineItemsDtoList") List<OrderLineItemsDto> orderLineItemsDtoList) {
        this.orderLineItemsDtoList = orderLineItemsDtoList;
    }

    public List<OrderLineItemsDto> getOrderLineItemsDtoList() {
        return orderLineItemsDtoList;
    }

    public void setOrderLineItemsDtoList(List<OrderLineItemsDto> orderLineItemsDtoList) {
        this.orderLineItemsDtoList = orderLineItemsDtoList;
    }
}
