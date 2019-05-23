package com.example.testoneclean.network;

import com.example.testoneclean.model.Container;
import com.example.testoneclean.model.ContainerToSend;
import com.example.testoneclean.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DataService {

    @GET("test")
    Call<Container> getProducts();

    @Headers("Content-Type: application/json")
    @POST("try.php")
    Call<ContainerToSend> sendProducts(@Body ContainerToSend products);
}
