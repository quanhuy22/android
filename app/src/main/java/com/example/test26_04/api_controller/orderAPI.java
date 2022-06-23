package com.example.test26_04.api_controller;

import com.example.test26_04.models.AddOrderResponse;
import com.example.test26_04.models.Order;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface orderAPI {

    static String API_BASE_URL = "http://192.168.181.1:5000/api/v1/orders/";

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    orderAPI apiService = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(orderAPI.class);

    @POST("add-order")
    Call<AddOrderResponse> addOrder(@Body Order order);

    @GET("all-pending-orders")
    Call<ArrayList<Order>> getPendingOrders();

    @GET("all-confirmed-orders")
    Call<ArrayList<Order>> getConfirmedOrders();

    @GET("all-cancelled-orders")
    Call<ArrayList<Order>> getCancelledOrders();

    @PUT("confirm-order")
    Call<String> putConfirmOrder(@Query("id") String id);

    @PUT("cancel-order")
    Call<String> putCancelOrder(@Query("id") String id);

}
