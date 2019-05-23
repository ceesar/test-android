package com.example.testoneclean.model;

import java.util.List;

public class Product {

    private String id;
    private String name;
    private int available;
    private List<Color> colors;

    public Product() {

    }

    public Product(String id, String name, int available, List<Color> colors) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.colors = colors;
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

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }
}