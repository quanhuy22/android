package com.example.test26_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tao_don_xuat_hang extends AppCompatActivity {
    Button btnFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_don_xuat_hang);
        btnFinish=(Button) findViewById(R.id.btnFinishXuat);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(tao_don_xuat_hang.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}