package com.example.test26_04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.test26_04.models.ProductIdAndQuantity;
import com.example.test26_04.models.Product;
import com.example.test26_04.utils.ThemMoiProductAdapter;

import java.util.ArrayList;

public class them_moi extends AppCompatActivity {
    private Button  btnTaoDon;
    private RecyclerView rvProductList;
    private ArrayList<Product> productList;
    private ThemMoiProductAdapter adapter;
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_moi);
        btnTaoDon = (Button)findViewById(R.id.btnTaoDon);

        rvProductList = findViewById(R.id.order_them_moi_listView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvProductList.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration= new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvProductList.addItemDecoration(itemDecoration);

        Intent intent = getIntent();
        productList = (ArrayList<Product>) intent.getSerializableExtra("Product list");

        adapter = new ThemMoiProductAdapter(this, productList);
        rvProductList.setAdapter(adapter);

        btnTaoDon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                ArrayList<ProductIdAndQuantity> productIdAndQuantities = adapter.getProductIdAndQuantities();
                ArrayList<Product> products = adapter.getmListProduct();
                ArrayList<ProductIdAndQuantity> actualProductIdAndQuantities = new ArrayList<>();
                ArrayList<Product> actualProducts = new ArrayList<>();

                for(int i = 0; i < productIdAndQuantities.size(); i++){
                    if (productIdAndQuantities.get(i).getQuantity() > 0){
                        actualProductIdAndQuantities.add(productIdAndQuantities.get(i));
                        actualProducts.add(products.get(i));
                    }
                }

                Log.e("CHECK BUYING LIST", String.valueOf(actualProductIdAndQuantities.size()));
                Intent intent = new Intent(them_moi.this, confirm.class);
                intent.putExtra("productIdAndQuantities", actualProductIdAndQuantities);
                intent.putExtra("buyingProductList", actualProducts);
                startActivity(intent);
            }
        });
    }



}