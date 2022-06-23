package com.example.test26_04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test26_04.api_controller.loginAPI;
import com.example.test26_04.models.User;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        MaterialButton btnSignup = (MaterialButton) findViewById(R.id.btnSignup);
        TextView usernameTv = (TextView) findViewById(R.id.e_username);
        TextView passwordTv = (TextView) findViewById(R.id.e_password);
        TextView rePasswordTv = (TextView) findViewById(R.id.e_Confirm_password);
        TextView emailTv = (TextView) findViewById(R.id.e_mail);
        TextView fullNameTv = (TextView) findViewById(R.id.e_fullname);

        // click button sign in to back login UI


        // click button Create account
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameTv.getText().toString();
                String password = passwordTv.getText().toString();
                String rePassword = rePasswordTv.getText().toString();
                String fullName = fullNameTv.getText().toString();
                String email = emailTv.getText().toString();

                if (!password.equals(rePassword)){
                    Toast.makeText(SignUpActivity.this, "Incorrectly confirmed password", Toast.LENGTH_LONG).show();
                } else {
                    User signUpUser = new User(username, fullName, password, email);
                    callSignUpApi(signUpUser);
                }
            }
        });
    }

    private void callSignUpApi(User user){
        loginAPI.apiService.signup(user).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String msg = response.body();
                if (msg.equalsIgnoreCase("sign up successfully")){
                    Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}