package com.example.dku_dow_dpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class BabpickselectActivity extends AppCompatActivity {
    Button secondbtn;
    ImageButton backbtn;
    EditText edtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babpickselect);

        backbtn = findViewById(R.id.button);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickselectActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        secondbtn = findViewById(R.id.second_btn);
        secondbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickselectActivity.this, BabpickresultActivity.class);
                startActivity(intent);
            }
        });
    }
}