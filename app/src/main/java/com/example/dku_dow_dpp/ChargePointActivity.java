package com.example.dku_dow_dpp;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ChargePointActivity extends AppCompatActivity {

    private Button point_charge, charge_list, user_input;
    private Button five, ten, twenty, thirty, fifty;
    private ImageButton back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargepoint);

        five = findViewById(R.id.five_thousands);
        ten = findViewById(R.id.ten_thousands);
        twenty = findViewById(R.id.twenty_thousands);
        thirty = findViewById(R.id.thirty_thousands);
        fifty = findViewById(R.id.fifty_thousands);
        user_input = findViewById(R.id.user_input);
        point_charge = findViewById(R.id.point_charge);
        charge_list = findViewById(R.id.charge_list);
        back_btn = findViewById(R.id.button);

        point_charge.setPaintFlags(point_charge.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChargePointActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        charge_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChargePointActivity.this, ChargeListActivity.class);
                startActivity(intent);
            }
        });

    }
}
