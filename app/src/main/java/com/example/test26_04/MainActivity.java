package com.example.test26_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test26_04.api_controller.orderAPI;
import com.example.test26_04.api_controller.productAPI;
import com.example.test26_04.models.Order;
import com.example.test26_04.models.Product;
import com.example.test26_04.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ImageButton btnProduct ;
    private ImageButton btnStatictic ;
    private ImageButton btnOrder ;
    private ImageButton btnStorage ;
    private TextView tvFullName;
    private ImageButton btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnProduct = findViewById(R.id.btn_product);
        btnOrder = findViewById(R.id.btn_order);
        btnStorage = findViewById(R.id.btn_storage);
        btnStatictic = findViewById(R.id.btn_statictic);
        tvFullName = findViewById(R.id.tv_main_username);
        btnLogout = findViewById(R.id.imageButton_logout);

        Intent intent = getIntent();
        User user = (User)intent.getSerializableExtra("user");

        tvFullName.setText(user.getFullName());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callGetAllProductsAPI(ProductActivity.class);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callGetPendingOrdersAPI();
            }
        });

        btnStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callGetAllProductsAPI(StorageActivity.class);
            }
        });
    }

    private void callGetPendingOrdersAPI(){
        Intent intent = new Intent(MainActivity.this, OrderActivity.class);

        orderAPI.apiService
                .getPendingOrders()
                .enqueue(new Callback<ArrayList<Order>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Order>> call, Response<ArrayList<Order>> response) {
                        ArrayList<Order> pendingOrders = response.body();
                        intent.putExtra("pendingOrders", pendingOrders);

                        orderAPI.apiService
                                .getConfirmedOrders()
                                .enqueue(new Callback<ArrayList<Order>>() {
                                    @Override
                                    public void onResponse(Call<ArrayList<Order>> call, Response<ArrayList<Order>> response) {
                                        ArrayList<Order> confirmedOrders = response.body();
                                        intent.putExtra("confirmedOrders", confirmedOrders);

                                        orderAPI.apiService
                                                .getCancelledOrders()
                                                .enqueue(new Callback<ArrayList<Order>>() {
                                                    @Override
                                                    public void onResponse(Call<ArrayList<Order>> call, Response<ArrayList<Order>> response) {
                                                        ArrayList<Order> cancelledOrders = response.body();
                                                        intent.putExtra("cancelledOrders", cancelledOrders);
                                                        startActivity(intent);
                                                    }

                                                    @Override
                                                    public void onFailure(Call<ArrayList<Order>> call, Throwable t) {

                                                    }
                                                });
                                    }

                                    @Override
                                    public void onFailure(Call<ArrayList<Order>> call, Throwable t) {

                                    }
                                });
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Order>> call, Throwable t) {

                    }
                });






    }

    private void callGetAllProductsAPI(Class destination){
        productAPI.apiService.getAllProducts().enqueue(new Callback<ArrayList<Product>>() {

            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                ArrayList<Product> res = response.body();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, destination);
                intent.putExtra("Product list", res);
                startActivity(intent);
            }


            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Can not call API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}