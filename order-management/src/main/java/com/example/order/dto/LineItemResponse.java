package com.example.order.dto;

public class LineItemResponse {
    private String id;
    private double quantity;

    public LineItemResponse( String id,double quantity) {
        this.id = id;

        this.quantity = quantity;

    }

    public String getId() {
        return id;
    }


    public double getQuantity() {
        return quantity;
    }

}
