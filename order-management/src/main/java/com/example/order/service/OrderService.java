package com.example.order.service;

import com.example.order.dto.LineItemResponse;
import com.example.order.dto.OrderLineItemsDto;
import com.example.order.dto.OrderRequest;
import com.example.order.dto.OrderResponse;
import com.example.order.model.Order;
import com.example.order.model.OrderLineItems;
import com.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository OrderRepository;
    private final WebClient webClient;


    @Autowired
    public OrderService(OrderRepository OrderRepository, WebClient.Builder webClient) {
        this.OrderRepository = OrderRepository;
        this.webClient = webClient.baseUrl("http://localhost:8082/api/item").build();
    }


    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToOrderLineItems).toList();

        order.setOrderLineItemsList(orderLineItems);
        List<LineItemResponse> lineItemResponses = orderLineItems.stream()
                .map(orderLineItem -> new LineItemResponse(orderLineItem.getId(), orderLineItem.getQuantity()))
                .toList();
        boolean allProductsInStock= sendLineItemData(lineItemResponses);


        if(allProductsInStock){
            OrderRepository.save(order);
            return "Order Placed successfully!";
        } else {
            return "Product is not in stock, please try again later";
        }


    }
    public boolean sendLineItemData (List < LineItemResponse > lineItemResponses) {
         return Boolean.TRUE.equals(webClient.put()
                 .uri("/update-items")
                 .bodyValue(lineItemResponses)
                 .retrieve()
                 .bodyToMono(Boolean.class)
                 .block());
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setId(orderLineItemsDto.getId());
        orderLineItems.setName(orderLineItemsDto.getName());
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

