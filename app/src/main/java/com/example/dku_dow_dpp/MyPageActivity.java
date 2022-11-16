package com.example.dku_dow_dpp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MyPageActivity extends AppCompatActivity {

    private ImageButton back_btn;
    private Button profile_change, pw_change, coupon_used_list;
    private Button point_charge, app_settings, account_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        back_btn = findViewById(R.id.button);
        profile_change = findViewById(R.id.profile_change);
        pw_change = findViewById(R.id.pw_change);
        coupon_used_list = findViewById(R.id.coupon_used_list);
        point_charge = findViewById(R.id.point_charge);
        app_settings = findViewById(R.id.app_settings);
        account_delete = findViewById(R.id.account_delete);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        point_charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, PointChargeActivity.class);
                startActivity(intent);
            }
        });
    }
}