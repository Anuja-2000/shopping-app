package com.example.order.service;

import com.example.order.config.WebClientConfig;
import com.example.order.dto.ItemResponse;
import com.example.order.dto.OrderLineItemsDto;
import com.example.order.dto.OrderRequest;
import com.example.order.dto.OrderResponse;
import com.example.order.model.Order;
import com.example.order.model.OrderLineItems;
import com.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
                .map(this::mapToOrderLineItems).toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> ids = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getId)
                .toList();
        List<Double> qty = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getQuantity)
                .toList();

        // Call Inventory Service, and place order if product is in
        // stock
        boolean allProductsInStock = Boolean.TRUE.equals(webClient.get()
                .uri("http://localhost:8082/api/item",
                        uriBuilder -> uriBuilder.queryParam("id", ids,"qty",qty).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());

        if(allProductsInStock){
            OrderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setId(orderLineItemsDto.getId());
        orderLineItems.setName(orderLineItemsDto.getName());
        orderLineItems.setAddress(orderLineItemsDto.getAddress());
        orderLineItems.setDescription(orderLineItemsDto.getDescription());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());

        return orderLineItems;
    }

    private OrderResponse mapToOrderResponse (Order order){
        return new OrderResponse(
          order.getOrderNumber(),
          order.getOrderLineItemsList()
        );
    }

    public List<OrderResponse> getAllItems(){
        List<Order> items = OrderRepository.findAll();
        return items.stream().map(this::mapToOrderResponse).toList();
    }


    public boolean deleteOrder(String id) {
        Order order = OrderRepository.findById(id).orElse(null);
        if (order != null) {
            OrderRepository.delete(order);
            return true;
        }
        return false;
    }
}

