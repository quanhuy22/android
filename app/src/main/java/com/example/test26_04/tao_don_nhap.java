package com.example.test26_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test26_04.api_controller.productAPI;
import com.example.test26_04.models.ImportingProduct;
import com.example.test26_04.models.Product;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class tao_don_nhap extends AppCompatActivity {
    Button btnFinish;
    int importingNumber;
    private ArrayList<Product> updatedProductList;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_don_nhap);
        btnFinish=(Button) findViewById(R.id.btnFinishNhap);

        sp = getSharedPreferences("storage", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        Product importingProduct = (Product) intent.getSerializableExtra("importing product");

        TextView importingProductName = findViewById(R.id.importing_product_name);
        importingProductName.setText(importingProduct.getName());

        TextView importingPrice = (TextView) findViewById(R.id.importing_price);
        importingPrice.setText(String.valueOf(importingProduct.getImportPrice()));

        TextView importingTotalPrice = findViewById(R.id.importing_total_price);

        EditText importingStock = findViewById(R.id.editText_stock);


        importingStock.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    importingNumber = Integer.parseInt(String.valueOf(importingStock.getText()));
                    float totalPrice = importingProduct.getImportPrice() * importingNumber;
                    importingTotalPrice.setText(String.valueOf(totalPrice));
                }
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = importingStock.getText().toString();
                if (!number.equals("")){
                    callImportProductAPI(importingProduct.get_id(), Integer.parseInt(number));
                } else {
                    Toast.makeText(
                            tao_don_nhap.this,
                            "Please enter the number of product that you want to import",
                            Toast.LENGTH_SHORT
                    ).show();
                }

            }
        });
    }

    private void callImportProductAPI(String _id, int importingNumber){
        productAPI.apiService.postImportProduct(new ImportingProduct(_id, importingNumber))
                .enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        SharedPreferences.Editor editor = sp.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(response.body());
                        editor.putString("import product", json);
                        editor.commit();
                        Toast.makeText(tao_don_nhap.this, "Successfully imported", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(tao_don_nhap.this, StorageActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        Toast.makeText(tao_don_nhap.this, "Can not call API", Toast.LENGTH_SHORT).show();
                    }
                });

    }


}