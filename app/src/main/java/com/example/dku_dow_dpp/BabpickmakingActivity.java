package com.example.dku_dow_dpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;

public class BabpickmakingActivity extends AppCompatActivity {
    Button makingbtn;
    ImageButton backbtn;
    TimePicker mTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babpickmaking);

        Intent getstr = getIntent();
        String food = getstr.getStringExtra("food");

        backbtn = findViewById(R.id.backbutton);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickmakingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        makingbtn = findViewById(R.id.making_btn);
        makingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickmakingActivity.this, Babpickresult2Activity.class);

                intent.putExtra("food", food);
                String hour = "";
                String min = "";
                mTimePicker = (TimePicker) findViewById(R.id.selecttime);
                if (android.os.Build.VERSION.SDK_INT >= 24) {
                    hour = Integer.toString(mTimePicker.getHour());
                    min = Integer.toString(mTimePicker.getMinute());
                }

                intent.putExtra("hour", hour);
                intent.putExtra("min", min);

                startActivity(intent);
            }
        });


    }
}