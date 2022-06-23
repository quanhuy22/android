package com.example.test26_04;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test26_04.api_controller.productAPI;
import com.example.test26_04.models.Product;
import com.example.test26_04.utils.RealPathUtil;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_ProductActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 20;
    private EditText txtInputName;
    private EditText txtInputPrice;
    private EditText txtInputCategory;
    private EditText txtInputDescription;
    private EditText txtInputImportPrice;
    private Button btnClear;
    private Button btnAdd;
    private Product addedProduct = null;
    private SharedPreferences sp;
    private ArrayList<Product> updatedProductList;
    private Gson gson = new Gson();
    private Uri mUri;
    private ImageView imageInput;
    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if (data == null){
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imageInput.setImageBitmap(bmp);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


        //
        sp = getSharedPreferences("product", Context.MODE_PRIVATE);
        txtInputName = findViewById(R.id.txt_input_Name_Product);
        txtInputPrice = findViewById(R.id.txt_input_Price_Product);
        txtInputCategory = findViewById(R.id.txt_category);
        txtInputDescription = findViewById(R.id.txt_input_Description_Product);
        txtInputImportPrice = findViewById(R.id.txt_input_import_Price_Product);
        imageInput = findViewById(R.id.image_input);
        btnClear = findViewById(R.id.btn_clear);
        btnAdd = findViewById(R.id.btn_add);


        imageInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUri != null){
                    callPostAddProductAPI();
                } else {
                    Toast.makeText(Add_ProductActivity.this, "Please choose product image before submit", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void callPostAddProductAPI(){

        String name = txtInputName.getText().toString();
        float price = Float.parseFloat(txtInputPrice.getText().toString().trim());
        float importPrice = Float.parseFloat(txtInputImportPrice.getText().toString().trim());
        String description = txtInputDescription.getText().toString().trim();
        String category = txtInputCategory.getText().toString().trim();

        RequestBody requestBodyName = RequestBody.create(MediaType.parse("multipart/form-data"), name);
        RequestBody requestBodyPrice = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(price));
        RequestBody requestBodyImportPrice = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(importPrice));
        RequestBody requestBodyCategory = RequestBody.create(MediaType.parse("multipart/form-data"), category);
        RequestBody requestBodyDescription = RequestBody.create(MediaType.parse("multipart/form-data"), description);

        String imageRealPath = RealPathUtil.getRealPath(this, mUri);
        File file = new File(imageRealPath);
        RequestBody requestBodyImage = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBodyImage = MultipartBody.Part.createFormData("image", file.getName(), requestBodyImage);


        productAPI.apiService
                .postAddProduct(
                    requestBodyName,
                    requestBodyImportPrice,
                    requestBodyPrice,
                    requestBodyDescription,
                    requestBodyCategory,
                    multipartBodyImage)
                .enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        addedProduct = response.body();
                        Toast.makeText(Add_ProductActivity.this, "Successfully adding new product", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        Toast.makeText(Add_ProductActivity.this, "Can not call API", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void onClickRequestPermission(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openGallery();
        } else {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permissions, MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }
    }

    private void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select image"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            try {
                Bitmap bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                imageInput.setImageBitmap(bmp);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences.Editor editor = sp.edit();
        if (addedProduct != null){
            String json = gson.toJson(addedProduct);
            editor.putString("added product", json);
            editor.commit();
            addedProduct = null;
        }

    }

}