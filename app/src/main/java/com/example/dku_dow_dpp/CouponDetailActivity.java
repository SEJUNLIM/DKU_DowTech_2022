package com.example.dku_dow_dpp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Objects;

public class CouponDetailActivity extends AppCompatActivity {

    ImageButton backBtn;
    TextView title;
    TextView productPrice;
    TextView productDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_detail);
        Intent i = getIntent();
        HashMap<String,Object> docs = (HashMap<String, Object>) i.getSerializableExtra("coupon");

        Log.d("~~TAG~~",docs.toString());
        title = findViewById(R.id.title);
        title.setText((CharSequence) docs.get("name"));

        productPrice = findViewById(R.id.productPrice);
        productPrice.setText(Objects.requireNonNull(docs.get("price"))+"원");

        productDetail = findViewById(R.id.productDetail);
        productDetail.setText((CharSequence) docs.get("description"));

        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> onBackPressed());
    }
}
