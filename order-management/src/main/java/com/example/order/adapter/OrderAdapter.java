package com.example.order.adapter;

import com.example.order.dto.OrderLineItemsDto;
import com.example.order.model.OrderLineItems;

public class OrderAdapter {
    private final OrderLineItemsDto orderLineItemsDto;

    public OrderAdapter(OrderLineItemsDto orderLineItemsDto) {
        this.orderLineItemsDto = orderLineItemsDto;
    }

    public  OrderLineItems mapTomapToOrderLineItems() {

        OrderLineItems orderLineItems = new OrderLineItems(
                orderLineItemsDto.getId(),
                orderLineItemsDto.getName(),
                orderLineItemsDto.getDescription(),
                orderLineItemsDto.getQuantity(),
                orderLineItemsDto.getPrice()
        );
        return orderLineItems;
    }
}
