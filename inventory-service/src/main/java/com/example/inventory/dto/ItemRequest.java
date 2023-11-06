package com.example.inventory.dto;

public class ItemRequest {
    private String name;
    private String description;
    private double quantity;
    private double price;
    private String addedBy;
    private String lastUpdated;


    public ItemRequest(String name, String description, double quantity, double price, String addedBy, String lastUpdated) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.addedBy = addedBy;
        this.lastUpdated = lastUpdated;
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

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
