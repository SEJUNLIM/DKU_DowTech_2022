package com.example.dku_dow_dpp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MycouponActivity  extends AppCompatActivity {

    Button buyCoupon;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycoupon);

        buyCoupon = findViewById(R.id.buyCouponButton);
        buyCoupon.setOnClickListener(view -> {
            Intent intent = new Intent(MycouponActivity.this,BuyCouponActivity.class);
            startActivity(intent);
        });

        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> onBackPressed());
    }
}
