package com.example.test26_04;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test26_04.api_controller.loginAPI;
import com.example.test26_04.models.LoginBody;
import com.example.test26_04.models.User;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView us = (TextView) findViewById(R.id.username); // us = username
        TextView ps = (TextView) findViewById(R.id.password); // ps = password
        TextView su = (TextView) findViewById(R.id.signUp);  // su = sign up
        MaterialButton btnLogin = (MaterialButton) findViewById(R.id.btnLogin);

        // Login process
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = us.getText().toString();
                String password = ps.getText().toString();
                if (username.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                } else {
                    callLoginApi(username, password);
                }


            }
        });

        // Sign-in process
        su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
            }
        });
    }

    private void callLoginApi(String username, String password){
        User user = new User(username, password);
        loginAPI.apiService.login(user).enqueue(new Callback<LoginBody>() {
            @Override
            public void onResponse(Call<LoginBody> call, Response<LoginBody> response) {
                LoginBody res = response.body();
                String msg = res.getMessage();
                User fullUser = res.getUser();

                if (msg.equalsIgnoreCase("ok")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user", fullUser);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Can not call API", Toast.LENGTH_SHORT).show();
            }
        });
    }

}