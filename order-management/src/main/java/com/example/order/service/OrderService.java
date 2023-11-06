package com.example.order.service;

import com.example.order.config.WebClientConfig;
import com.example.order.dto.ItemResponse;
import com.example.order.dto.OrderLineItemsDto;
import com.example.order.dto.OrderRequest;
import com.example.order.model.Order;
import com.example.order.model.OrderLineItems;
import com.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository OrderRepository;
    private final WebClient webClient;


    @Autowired
    public OrderService(OrderRepository OrderRepository, WebClient webClient) {
        this.OrderRepository = OrderRepository;
        this.webClient = webClient;
    }


    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto).toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> ids = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getId)
                .toList();

        // Call Inventory Service, and place order if product is in
        // stock
        boolean allProductsInStock = Boolean.TRUE.equals(webClient.get()
                .uri("http://localhost:8082/api/item",
                        uriBuilder -> uriBuilder.queryParam("id", ids).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());

        if(allProductsInStock){
            OrderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setId(orderLineItemsDto.getId());
        orderLineItems.setName(orderLineItemsDto.getName());
        orderLineItems.setDescription(orderLineItemsDto.getDescription());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());

        return orderLineItems;
    }
}

