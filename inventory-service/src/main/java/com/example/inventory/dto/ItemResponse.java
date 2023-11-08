package com.example.inventory.dto;

public class ItemResponse {
    private String id;
    private String name;
    private String description;
    private double quantity;
    private double price;
    private String addedBy;
    private String lastUpdated;

    public ItemResponse(String id, String name, String description, double quantity, double price, String addedBy, String lastUpdated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.addedBy = addedBy;
        this.lastUpdated = lastUpdated;
    }

    public ItemResponse(String id, String name, String description, Double itemQty, String addedBy, double price) {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
