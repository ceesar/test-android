package com.example.testoneclean.network;

import com.example.testoneclean.model.Container;
import com.example.testoneclean.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("test")
    Call<Container> getProducts();
}
