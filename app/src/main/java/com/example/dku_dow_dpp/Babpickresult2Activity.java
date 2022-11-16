package com.example.dku_dow_dpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Babpickresult2Activity extends AppCompatActivity {
    ImageButton backbtn;
    Button returnbtn;
    TextView timetext;
    TextView nametext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babpickresult2);

        backbtn = findViewById(R.id.button);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Babpickresult2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        returnbtn = findViewById(R.id.return_btn);
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Babpickresult2Activity.this, MainActivity.class);

                startActivity(intent);
            }
        });

        Intent getstr = getIntent();

        String food = getstr.getStringExtra("food");

        String hour = getstr.getStringExtra("hour");
        String min = getstr.getStringExtra("min");

        String time = hour + " 시   " + min +" 분";

        nametext = findViewById(R.id.nametext);
        nametext.setText(food);
        timetext = findViewById(R.id.timetext);
        timetext.setText(time);
    }
}