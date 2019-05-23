package com.example.testoneclean.model;

public class ProductToSend {

    private String id;
    private String color;
    private String size;
    private int amount;

    public ProductToSend() {
    }

    public ProductToSend(String id, String color, String size, int amount) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
