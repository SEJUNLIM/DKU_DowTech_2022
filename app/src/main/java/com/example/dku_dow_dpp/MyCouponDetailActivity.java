package com.example.dku_dow_dpp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.Timestamp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
        ImageView productImage = findViewById(R.id.productImage);
        Intent i = getIntent();
        HashMap<String,Object> docs = (HashMap<String, Object>) i.getSerializableExtra("coupon");
        String title = Objects.requireNonNull(docs.get("brand")).toString();
        String productName = Objects.requireNonNull(docs.get("name")).toString();
        String imageInfo = title + " " + productName + ".jpg";
        Log.d("IMAGE TAG",imageInfo);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(imageInfo);
        storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(MyCouponDetailActivity.this).load(uri).into(productImage);
            Log.d("IMAGE TAG","SUCCESS!");
        });
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy.MM.dd");
        Timestamp buyTS = (Timestamp) docs.get("buyDate");
        assert buyTS != null;
        String buyDate = sdt.format(buyTS.toDate());
        Timestamp expTS = (Timestamp) docs.get("expDate");
        assert expTS != null;
        String expDate = sdt.format(expTS.toDate());
        String detailString = "구매자 : "+Objects.requireNonNull(docs.get("buyUser"))+"\n구매 날짜 : "+buyDate+"\n유효 기한 : "+expDate;
        brand = findViewById(R.id.title);
        brand.setText(title);
        name = findViewById(R.id.productName);
        name.setText(productName);
        detail = findViewById(R.id.detailText);
        detail.setText(detailString);


        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> onBackPressed());
    }
}
