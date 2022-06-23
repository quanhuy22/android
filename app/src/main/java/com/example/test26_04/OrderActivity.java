package com.example.test26_04;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.loader.app.LoaderManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.test26_04.api_controller.orderAPI;
import com.example.test26_04.api_controller.productAPI;
import com.example.test26_04.models.Order;
import com.example.test26_04.models.Product;
import com.example.test26_04.utils.ConfirmedAndCancelledOrderAdapter;
import com.example.test26_04.utils.OrderAdapter2;
import com.example.test26_04.utils.OrderItemViewModel;
import com.example.test26_04.utils.ViewPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
OrderActivity extends AppCompatActivity {

    private FloatingActionButton btnAdd;
    private RecyclerView rvPendingOrders;
    private ViewPager viewPager;
    private TabItem tiPendingOrders;
    private TabItem tiConfirmedOrders;
    private TabItem tiCancelledOrders;
    private TabLayout tabLayout;
    private ArrayList<Order> pendingOrders;
    private ArrayList<Order> confirmedOrders;
    private ArrayList<Order> cancelledOrders;
    private OrderAdapter2 pendingOrderAdapter;
    private ConfirmedAndCancelledOrderAdapter confirmedOrderAdapter;
    private ConfirmedAndCancelledOrderAdapter cancelledOrderAdapter;
    private SharedPreferences sp;
    private ViewPagerAdapter viewPagerAdapter;
    private Fragment mFragment;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        btnAdd = (FloatingActionButton)findViewById(R.id.btnAddnew);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        mFragment = getFragmentManager().findFragmentByTag("PendingOrdersFragment");

        Intent intent = getIntent();
        pendingOrders = (ArrayList<Order>) intent.getSerializableExtra("pendingOrders");
        confirmedOrders = (ArrayList<Order>) intent.getSerializableExtra("confirmedOrders");
        cancelledOrders = (ArrayList<Order>) intent.getSerializableExtra("cancelledOrders");

        Log.e("PENDING ORDER", String.valueOf(pendingOrders.size()));
        Log.e("CONFIRMED ORDER", String.valueOf(confirmedOrders.size()));
        Log.e("CANCELLED ORDER", String.valueOf(cancelledOrders.size()));
        viewPagerAdapter = new ViewPagerAdapter(this, pendingOrders, confirmedOrders, cancelledOrders, getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callGetAllProductsAPI();
            }
        });

    }

    private void callGetAllProductsAPI(){
        productAPI.apiService.getAllProducts().enqueue(new Callback<ArrayList<Product>>() {

            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                ArrayList<Product> res = response.body();
                Intent intent = new Intent();
                intent.setClass(OrderActivity.this, them_moi.class);
                intent.putExtra("Product list", res);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Toast.makeText(OrderActivity.this, "Can not call API", Toast.LENGTH_SHORT).show();
            }
        });
    }

}