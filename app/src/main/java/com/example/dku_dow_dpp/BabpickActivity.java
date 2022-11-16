package com.example.dku_dow_dpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BabpickActivity extends AppCompatActivity {

    private Button firstbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babpick);

        firstbtn = findViewById(R.id.first_btn);
        firstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickActivity.this, BabpickselectActivity.class);
                startActivity(intent);
            }
        });

    }
}