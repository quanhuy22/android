package com.example.test26_04.api_controller;

import com.example.test26_04.models.ImportingProduct;
import com.example.test26_04.models.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface productAPI {

    static String API_BASE_URL = "http://192.168.181.1:5000/api/v1/products/";

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    productAPI apiService = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(productAPI.class);

    @GET("all-products")
    Call<ArrayList<Product>> getAllProducts();

    @POST("import-product")
    Call<Product> postImportProduct(@Body ImportingProduct product);

    @Multipart
    @POST("add-product")
    Call<Product> postAddProduct(@Part("name") RequestBody name,
                                 @Part("importingPrice") RequestBody importingPrice,
                                 @Part("price") RequestBody price,
                                 @Part("description") RequestBody description,
                                 @Part("categories") RequestBody category,
                                 @Part MultipartBody.Part image);

    @DELETE("delete-product")
    Call<String> deleteProduct(@Query("id") String id);



}
