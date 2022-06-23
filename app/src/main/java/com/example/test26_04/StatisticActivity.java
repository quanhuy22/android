package com.example.test26_04;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.test26_04.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class StatisticActivity extends AppCompatActivity implements View.OnClickListener {

    private Button  btn_Statistic;
    private EditText edtxtStartDate, edtxtEndDate;
    private TextView tvDoanhThu,tvGiaVon,tvLoiNhuan;
    SimpleDateFormat sdf ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        sdf = new SimpleDateFormat("yyyy/MM/dd");

        // editText
        edtxtStartDate = findViewById(R.id.edtxt_StartDate);
        edtxtEndDate = findViewById(R.id.edtxt_EndDate);

        // Button

        btn_Statistic = findViewById(R.id.btn_ThongKe);

        // textview
        tvDoanhThu = findViewById(R.id.txtShow_DoanhThu);
        tvGiaVon = findViewById(R.id.txtShow_GiaVon);
        tvLoiNhuan = findViewById(R.id.txtShow_LoiNhuan);


        edtxtStartDate.setOnClickListener(this);
        edtxtEndDate.setOnClickListener(this);

        // handler



        //handler event onclick button Statistic

        btn_Statistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String startDate = edtxtStartDate.getText().toString();
//                String endDate = edtxtEndDate.getText().toString();
//
//                tvDoanhThu.setText(+"đ"); // thiếu hàm dao tính toán ra tổng số , hàm DAO này được gọi đến ở trước dấu +
//                // " ví dụ DoanhThuDAO.getDoanhThu() + "đ" " đó
//
//                tvGiaVon.setText(+"đ"); // thiếu hàm dao tính toán ra tổng số , hàm DAO này được gọi đến ở trước dấu +
//                // " ví dụ DoanhThuDAO.getGiaVon() + "đ" " đó
//
//                tvLoiNhuan.setText(+"đ"); // thiếu hàm dao tính toán ra tổng số , hàm DAO này được gọi đến ở trước dấu +
//                // " ví dụ DoanhThuDAO.getLoiNhuan() + "đ" " đó
//
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == edtxtStartDate){
            Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                    edtxtStartDate.setText(yy+"/"+(mm+1)+"/"+dd);
                }
            },y,m,d);
            dialog.show();
        }

        if (view == edtxtEndDate){
            Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                    edtxtEndDate.setText(yy+"/"+(mm+1)+"/"+dd);
                }
            },y,m,d);
            dialog.show();
        }
    }
}