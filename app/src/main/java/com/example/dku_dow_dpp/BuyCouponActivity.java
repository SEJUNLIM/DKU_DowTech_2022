package com.example.dku_dow_dpp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class BuyCouponActivity  extends AppCompatActivity {

    Button restaurant;
    Button cafe;
    Button convenient;
    Button office;
    Button beauty;
    Button book;
    Button internet;
    Button culture;
    ImageButton backBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_coupon);

        intent = new Intent(BuyCouponActivity.this,CouponListActivity.class);
        restaurant = findViewById(R.id.restaurant);
        cafe = findViewById(R.id.cafe);
        convenient = findViewById(R.id.convenient);
        office = findViewById(R.id.office);
        beauty = findViewById(R.id.beauty);
        book = findViewById(R.id.book);
        internet = findViewById(R.id.internetLecture);
        culture = findViewById(R.id.culture);

        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> onBackPressed());
        restaurant.setOnClickListener(view -> categoryClick(0));
        cafe.setOnClickListener(view -> categoryClick(1));
        convenient.setOnClickListener(view -> categoryClick(2));
        office.setOnClickListener(view -> categoryClick(3));
        beauty.setOnClickListener(view -> categoryClick(4));
        book.setOnClickListener(view -> categoryClick(5));
        internet.setOnClickListener(view -> categoryClick(6));
        culture.setOnClickListener(view -> categoryClick(7));
    }

    protected void categoryClick(int i){
        intent.putExtra("category",i);
        startActivity(intent);
    }
}