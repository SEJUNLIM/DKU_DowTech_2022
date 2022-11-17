package com.example.dku_dow_dpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class BabpickselectActivity extends AppCompatActivity {
    Button firstbtn;
    Button secondbtn;
    Button thirdbtn;
    Button fourthbtn;
    ImageButton backbtn;
    EditText et_name;
    EditText et_time;
    String getname;
    String gettime;

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

        firstbtn = findViewById(R.id.firstsend);
        firstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickselectActivity.this, BabpickresultActivity.class);
                et_name = findViewById(R.id.getname1);
                getname = et_name.getText().toString();

                et_time = findViewById(R.id.gettime1);
                gettime = et_time.getText().toString();

                intent.putExtra("getname", getname);
                intent.putExtra("gettime", gettime);

                startActivity(intent);
            }
        });

        secondbtn = findViewById(R.id.secondsend);
        secondbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickselectActivity.this, BabpickresultActivity.class);
                et_name = findViewById(R.id.getname2);
                getname = et_name.getText().toString();

                et_time = findViewById(R.id.gettime2);
                gettime = et_time.getText().toString();

                intent.putExtra("getname", getname);
                intent.putExtra("gettime", gettime);

                startActivity(intent);
            }
        });

        thirdbtn = findViewById(R.id.thirdsend);
        thirdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickselectActivity.this, BabpickresultActivity.class);
                et_name = findViewById(R.id.getname3);
                getname = et_name.getText().toString();

                et_time = findViewById(R.id.gettime3);
                gettime = et_time.getText().toString();

                intent.putExtra("getname", getname);
                intent.putExtra("gettime", gettime);

                startActivity(intent);
            }
        });

        fourthbtn = findViewById(R.id.fourthsend);
        fourthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickselectActivity.this, BabpickresultActivity.class);
                et_name = findViewById(R.id.getname4);
                getname = et_name.getText().toString();

                et_time = findViewById(R.id.gettime4);
                gettime = et_time.getText().toString();

                intent.putExtra("getname", getname);
                intent.putExtra("gettime", gettime);

                startActivity(intent);
            }
        });
    }
}