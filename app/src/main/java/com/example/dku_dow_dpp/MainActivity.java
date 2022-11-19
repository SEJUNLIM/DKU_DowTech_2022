package com.example.dku_dow_dpp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    MyCouponFragment myCouponFragment;
    BabpickFragment babpickFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();
        myCouponFragment = new MyCouponFragment();
        babpickFragment = new BabpickFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.containers, mainFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottom_navi);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, mainFragment).commit();
                        return true;
                    case R.id.coupon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, myCouponFragment).commit();
                        return true;
                    case R.id.babpick:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, babpickFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }



}
