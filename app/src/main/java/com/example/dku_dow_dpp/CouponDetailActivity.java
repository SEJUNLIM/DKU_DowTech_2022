package com.example.dku_dow_dpp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Objects;

public class CouponDetailActivity extends AppCompatActivity {

    ImageButton backBtn;
    TextView title;
    TextView productPrice;
    TextView productDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_detail);
        Intent i = getIntent();
        ImageView productImage = findViewById(R.id.productImage);
        HashMap<String,Object> docs = (HashMap<String, Object>) i.getSerializableExtra("coupon");
        String brand = i.getStringExtra("brand");
        String productName = Objects.requireNonNull(docs.get("name")).toString();
        String imageInfo = brand + " " + productName + ".jpg";
        Log.d("IMAGE TAG",imageInfo);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(imageInfo);
        storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(CouponDetailActivity.this).load(uri).into(productImage);
            Log.d("IMAGE TAG","SUCCESS!");
        });


        title = findViewById(R.id.title);
        title.setText(brand);

        productPrice = findViewById(R.id.productPrice);
        productPrice.setText(Objects.requireNonNull(docs.get("price"))+"원");

        productDetail = findViewById(R.id.productDetail);
        productDetail.setText((CharSequence) docs.get("description"));

        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> onBackPressed());
    }
}
