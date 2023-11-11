package com.example.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderLineItemsDto {


    private String id;
    private String name;

    private String description;
    private double quantity;
    private double price;

    public OrderLineItemsDto(@JsonProperty("id") String id,@JsonProperty("name")String name,@JsonProperty("description") String description,@JsonProperty("quantity") double quantity,@JsonProperty("price") double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderLineItemsDto() {

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
