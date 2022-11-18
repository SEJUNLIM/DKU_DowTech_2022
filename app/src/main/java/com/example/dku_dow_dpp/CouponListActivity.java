package com.example.dku_dow_dpp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class CouponListActivity extends AppCompatActivity {
    Intent category = getIntent();
    int catNum = category.getIntExtra("category",0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        Log.d("@@@TAG@@@", "catNum: "+catNum);
    }
}
