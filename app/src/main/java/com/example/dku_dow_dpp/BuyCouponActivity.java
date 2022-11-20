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
        restaurant.setOnClickListener(view -> categoryClick("restaurant"));
        cafe.setOnClickListener(view -> categoryClick("cafe"));
        convenient.setOnClickListener(view -> categoryClick("convenient"));
        office.setOnClickListener(view -> categoryClick("office"));
        beauty.setOnClickListener(view -> categoryClick("beauty"));
        book.setOnClickListener(view -> categoryClick("book"));
        internet.setOnClickListener(view -> categoryClick("internetLecture"));
        culture.setOnClickListener(view -> categoryClick("culture"));
    }

    protected void categoryClick(String cat){
        intent.putExtra("category",cat);
        startActivity(intent);
    }
}