package com.example.testoneclean.model;

import java.util.List;

public class ContainerToSend {

    private List<ProductToSend> products;

    public ContainerToSend() {
    }

    public ContainerToSend(List<ProductToSend> products) {
        this.products = products;
    }

    public List<ProductToSend> getProducts() {
        return products;
    }

    public void setProducts(List<ProductToSend> products) {
        this.products = products;
    }
}
