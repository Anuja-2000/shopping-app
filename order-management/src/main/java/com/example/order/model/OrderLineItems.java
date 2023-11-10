package com.example.order.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document("orderLineItem")
public class OrderLineItems {
    @Id
    private String id;
    private String name;
    private String address;
    private String description;
    private double quantity;
    private double price;

    public OrderLineItems(String id, String name,String address ,String description, double quantity, double price) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderLineItems() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
