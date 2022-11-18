package com.example.dku_dow_dpp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CouponListActivity extends AppCompatActivity {

    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);

        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> onBackPressed());

        Intent category = getIntent();
        int catNum = category.getIntExtra("category",0);
        Log.d("@@@TAG@@@", "catNum: "+catNum);
    }
}
