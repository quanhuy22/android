package com.example.test26_04.api_controller;

import com.example.test26_04.models.LoginBody;
import com.example.test26_04.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

public interface loginAPI {

    static String API_BASE_URL = "http://192.168.181.1:5000/api/v1/authentication/";

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    loginAPI apiService = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(loginAPI.class);

    @POST("login")
    Call<LoginBody> login(@Body User user);

    @POST("signup")
    Call<String> signup(@Body User user);
}
