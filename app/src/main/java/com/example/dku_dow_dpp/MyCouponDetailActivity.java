package com.example.dku_dow_dpp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Objects;

public class MyCouponDetailActivity extends AppCompatActivity {
    ImageButton backBtn;
    TextView brand;
    TextView name;
    TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon_detail);
        Intent i = getIntent();

        HashMap<String,Object> docs = (HashMap<String, Object>) i.getSerializableExtra("coupon");
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy.MM.dd");
        Timestamp buyTS = (Timestamp) docs.get("buyDate");
        assert buyTS != null;
        String buyDate = sdt.format(buyTS.toDate());
        Timestamp expTS = (Timestamp) docs.get("expDate");
        assert expTS != null;
        String expDate = sdt.format(expTS.toDate());
        String detailString = "구매자 : "+Objects.requireNonNull(docs.get("buyUser"))+"\n구매 날짜 : "+buyDate+"\n유효 기한 : "+expDate;
        brand = findViewById(R.id.title);
        brand.setText(Objects.requireNonNull(docs.get("brand")).toString());
        name = findViewById(R.id.productName);
        name.setText(Objects.requireNonNull(docs.get("name")).toString());
        detail = findViewById(R.id.detailText);
        detail.setText(detailString);


        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> onBackPressed());
    }
}
