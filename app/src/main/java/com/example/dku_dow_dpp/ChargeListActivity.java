package com.example.dku_dow_dpp;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ChargeListActivity extends AppCompatActivity {

    private Button point_charge, charge_list, page;
    private Button oneMonth, threeMonth, sixMonth, year;
    private ImageButton back_btn, my_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargelist);

        my_btn = findViewById(R.id.my_btn);
        point_charge = findViewById(R.id.point_charge);
        charge_list = findViewById(R.id.charge_list);
        back_btn = findViewById(R.id.button);
        oneMonth = findViewById(R.id.one_month);
        threeMonth = findViewById(R.id.three_month);
        sixMonth = findViewById(R.id.six_month);
        year = findViewById(R.id.one_year);
        page = findViewById(R.id.page);

        charge_list.setPaintFlags(charge_list.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
        page.setPaintFlags(page.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        oneMonth.setPaintFlags(oneMonth.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);

        my_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChargeListActivity.this, MyPageActivity.class);
                startActivity(intent);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChargeListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        point_charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChargeListActivity.this, ChargePointActivity.class);
                startActivity(intent);
            }
        });
    }
}
