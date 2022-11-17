package com.example.dku_dow_dpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BabpickActivity extends AppCompatActivity {

    Button firstbtn;
    Button firstMakeBtn;
    Button secondbtn;
    Button secondMakeBtn;
    Button thirdbtn;
    Button thirdMakeBtn;
    EditText et_Text;
    String food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babpick);

        firstbtn = findViewById(R.id.first_btn);
        firstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickActivity.this, SplashActivity.class);
                startActivity(intent);
            }
        });

        firstMakeBtn = findViewById(R.id.first_btn2);
        firstMakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickActivity.this, BabpickmakingActivity.class);

                et_Text = findViewById(R.id.firstFood);
                food = et_Text.getText().toString();

                intent.putExtra("food", food);

                startActivity(intent);
            }
        });

        secondbtn = findViewById(R.id.second_btn);
        secondbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickActivity.this, SplashActivity.class);
                startActivity(intent);
            }
        });

        secondMakeBtn = findViewById(R.id.second_btn2);
        secondMakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickActivity.this, BabpickmakingActivity.class);

                et_Text = findViewById(R.id.secondFood);
                food = et_Text.getText().toString();

                intent.putExtra("food", food);

                startActivity(intent);
            }
        });

        thirdbtn = findViewById(R.id.third_btn);
        thirdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickActivity.this, SplashActivity.class);
                startActivity(intent);
            }
        });

        thirdMakeBtn = findViewById(R.id.third_btn2);
        thirdMakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickActivity.this, BabpickmakingActivity.class);

                et_Text = findViewById(R.id.thirdFood);
                food = et_Text.getText().toString();

                intent.putExtra("food", food);

                startActivity(intent);
            }
        });
    }
}