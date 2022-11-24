package com.example.dku_dow_dpp;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class CouponListActivity extends AppCompatActivity {

    ImageButton backBtn;
    Button sortBySc;
    Button sortByAbc;
    LinearLayout finalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Intent intentGet = getIntent();
        String category = intentGet.getStringExtra("category");
        Intent intentCouponDetail = new Intent(CouponListActivity.this,CouponDetailActivity.class);
        finalList = findViewById(R.id.finalList);

        // 생성된 뷰 저장 리스트(브랜드 단위)
        ArrayList<CouponListPerBrand> viewArray = new ArrayList<>();
        DocumentReference docref = db.document("coupon/"+category);
        docref.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                // 매장명 불러오기
                for (String name : (ArrayList<String>) Objects.requireNonNull(task.getResult().get("names"))) {
                    // 매장 단위 뷰 생성
                    View couponListView = LayoutInflater.from(getBaseContext()).inflate(R.layout.coupon_list_layout,null);
                    TextView brand = couponListView.findViewById(R.id.brandName);
                    TextView score = couponListView.findViewById(R.id.score);
                    AtomicReference<Double> scoreDouble = new AtomicReference<>(0.0);
                    LinearLayout linearLayout = couponListView.findViewById(R.id.listLinearLayout);
                    // 브랜드 명 setText
                    brand.setText(name);

                    CollectionReference colref = db.collection("coupon/"+category+"/"+name);
                    colref.get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            // 각 매장별 메뉴와 별점 불러오기
                            for(QueryDocumentSnapshot document:task1.getResult()){
                                Map<String, Object> couponData = document.getData();
                                if (document.getId().equals("score")) { // 별점 출력
                                    int total = ((Long) Objects.requireNonNull(couponData.get("total"))).intValue();
                                    int people = ((Long) Objects.requireNonNull(couponData.get("people"))).intValue();
                                    if (people>0) {
                                        scoreDouble.set((double) total / people);
                                    }
                                    String scoreText = String.format("★ %.1f / 5", scoreDouble.get());
                                    score.setText(scoreText);
                                } else {    // 메뉴와 가격 각 뷰에 setText
                                    View couponIndexView = LayoutInflater.from(getBaseContext()).inflate(R.layout.coupon_index_layout, null);
                                    Button theCoupon = couponIndexView.findViewById(R.id.menuPrice);
                                    TextView menu = couponIndexView.findViewById(R.id.menu);
                                    theCoupon.setOnClickListener(view -> couponClick(intentCouponDetail, name, (HashMap<String, Object>) couponData));
                                    theCoupon.setText("￦ "+Objects.requireNonNull(couponData.get("price")));
                                    menu.setText(Objects.requireNonNull(couponData.get("name")).toString());
                                    linearLayout.addView(couponIndexView);
                                }
                            }
                            CouponListPerBrand viewArrayContent = new CouponListPerBrand(couponListView,name,scoreDouble.get());
                            viewArray.add(viewArrayContent);
                        } else {
                            Log.d("@@@TAG@@@","Error getting documents: ", task.getException());
                        }
                    });
                }
            }else{
                Log.d("@@@TAG@@@", "Error getting documents: ", task.getException());
            }
        });

        Thread thread = new Thread() {
            public void run() {
                try {
                    Thread.sleep(700);
                    runOnUiThread(() -> {
                        Collections.sort(viewArray,new BrandScoreComparator());
                        screenReload(viewArray);
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> onBackPressed());

        sortBySc = findViewById(R.id.scoreOrder);
        sortBySc.setOnClickListener(view -> {
            Collections.sort(viewArray,new BrandScoreComparator());
            screenReload(viewArray);
        });
        sortByAbc = findViewById(R.id.abcOrder);
        sortByAbc.setOnClickListener(view -> {
            Collections.sort(viewArray,new BrandNameComparator());
            screenReload(viewArray);
        });
    }

    protected void couponClick(Intent intent, String name, Serializable docs){
        intent.putExtra("brand",name);
        intent.putExtra("coupon",docs);
        startActivity(intent);
        finish();
    }

    void screenReload(ArrayList<CouponListPerBrand> va){
        finalList.removeAllViews();
        for (CouponListPerBrand clpb: va) {
            finalList.addView(clpb.view);
        }
    }
}

class CouponListPerBrand {
    View view;
    String name;
    Double score;
    public CouponListPerBrand(View v, String n, Double sc){
        view = v;
        name = n;
        score = sc;
    }
}

class BrandScoreComparator implements Comparator<CouponListPerBrand>{
    @Override
    public int compare(CouponListPerBrand brand1, CouponListPerBrand brand2){
        if (brand1.score>brand2.score){
            return -1;
        } else if (brand1.score<brand2.score) {
            return 1;
        }
        return 0;
    }
}

class BrandNameComparator implements Comparator<CouponListPerBrand>{
    @Override
    public int compare(CouponListPerBrand brand1, CouponListPerBrand brand2){
        return brand1.name.compareTo(brand2.name);
    }
}
