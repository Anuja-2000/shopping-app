package com.example.order.dto;

public class ItemResponse {
    private String id;
    private String name;
    private String description;
    private double quantity;
    private double price;
    private String addedBy;
    private String lastUpdated;

    public ItemResponse(String id, String name,String address, String description, double quantity, double price, String addedBy, String lastUpdated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.addedBy = addedBy;
        this.lastUpdated = lastUpdated;
    }




    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

}
