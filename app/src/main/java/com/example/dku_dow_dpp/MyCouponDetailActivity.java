package com.example.dku_dow_dpp;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MyCouponDetailActivity extends AppCompatActivity {
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon_detail);

        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> onBackPressed());
    }
}
