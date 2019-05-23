package com.example.testoneclean.model;

import java.util.List;

public class Container {
    private List<Product> products;

    public Container() {
    }

    public Container(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
