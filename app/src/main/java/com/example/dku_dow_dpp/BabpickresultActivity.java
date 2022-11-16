package com.example.dku_dow_dpp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class BabpickresultActivity extends AppCompatActivity {
    ImageButton backbtn;
    Button returnbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babpickresult);

        backbtn = findViewById(R.id.button);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickresultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        returnbtn = findViewById(R.id.return_btn);
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickresultActivity.this, BabpickselectActivity.class);
                startActivity(intent);
            }
        });


    }
}