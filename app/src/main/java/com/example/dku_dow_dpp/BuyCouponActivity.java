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
        restaurant = findViewById(R.id.restaurant);
        cafe = findViewById(R.id.cafe);
        convenient = findViewById(R.id.convenient);
        office = findViewById(R.id.office);
        beauty = findViewById(R.id.beauty);
        book = findViewById(R.id.book);
        internet = findViewById(R.id.internetLecture);
        culture = findViewById(R.id.culture);
        intent = new Intent(BuyCouponActivity.this,CouponListActivity.class);

        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> onBackPressed());
        restaurant.setOnClickListener(view -> {
            categoryClick(0);
            startActivity(intent);
        });
        cafe.setOnClickListener(view -> {
            categoryClick(1);
            startActivity(intent);
        });
        convenient.setOnClickListener(view -> {
            categoryClick(2);
            startActivity(intent);
        });
        office.setOnClickListener(view -> {
            categoryClick(3);
            startActivity(intent);
        });
        beauty.setOnClickListener(view -> {
            categoryClick(4);
            startActivity(intent);
        });
        book.setOnClickListener(view -> {
            categoryClick(5);
            startActivity(intent);
        });
        internet.setOnClickListener(view -> {
            categoryClick(6);
            startActivity(intent);
        });
        culture.setOnClickListener(view -> {
            categoryClick(7);
            startActivity(intent);
        });
    }

    protected void categoryClick(int i){
        switch (i){
            case 0:
                intent.putExtra("category",0);
                break;
            case 1:
                intent.putExtra("category",1);
                break;
            case 2:
                intent.putExtra("category",2);
                break;
            case 3:
                intent.putExtra("category",3);
                break;
            case 4:
                intent.putExtra("category",4);
                break;
            case 5:
                intent.putExtra("category",5);
                break;
            case 6:
                intent.putExtra("category",6);
                break;
            default:
                intent.putExtra("category",7);
                break;
        }
    }
}