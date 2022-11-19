package com.example.dku_dow_dpp;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;
import java.util.Objects;

public class MycouponActivity extends AppCompatActivity {

    Button buyCoupon;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycoupon);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Intent intentMyCouponDetail = new Intent(MycouponActivity.this,MyCouponDetailActivity.class);

        CollectionReference colref = db.collection("user").document("lim").collection("coupon");
        colref.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                int i = 0;
                TableLayout tableLayout = findViewById(R.id.myCouponListTableLayout);
                TableRow newTableRow = new TableRow(getBaseContext());
                for(QueryDocumentSnapshot document:task.getResult()){
                    Map<String, Object> couponData = document.getData();
                    View myCouponListView = LayoutInflater.from(getBaseContext()).inflate(R.layout.my_coupon_list_layout,null);
                    Button theCoupon = myCouponListView.findViewById(R.id.myCoupon1);
                    int finalI = i;
                    theCoupon.setOnClickListener(view -> couponClick(intentMyCouponDetail,finalI));
                    String stringData = Objects.requireNonNull(couponData.get("brand")) +"\n"+ Objects.requireNonNull(couponData.get("name"));
                    theCoupon.setText(stringData);
                    if (i==0) {
                        TableRow tableRow = findViewById(R.id.tableRow);
                        tableRow.addView(myCouponListView);
                    } else if(i%2==1) {
                        newTableRow = new TableRow(getBaseContext());
                        newTableRow.addView(myCouponListView);
                    } else {
                        newTableRow.addView(myCouponListView);
                        tableLayout.addView(newTableRow);
                    }
                    i++;
                    Log.d("@@@TAG@@@", document.getId()+"=>"+document.getData());
                }
            } else {
                Log.d("@@@TAG@@@", "Error getting documents: ", task.getException());
            }
        });

        buyCoupon = findViewById(R.id.buyCouponButton);
        buyCoupon.setOnClickListener(view -> {
            Intent intentBuyCoupon = new Intent(MycouponActivity.this,BuyCouponActivity.class);
            startActivity(intentBuyCoupon);
            finish();
        });

        backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(view -> onBackPressed());
    }

    protected void couponClick(Intent intent, int i){
        intent.putExtra("couponNum",i);
        startActivity(intent);
        finish();
    }

}
//        myCoupon = findViewById(R.id.myCoupon0);
//        Thread thread = new Thread() {
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                    runOnUiThread(() -> {
//
//                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        thread.start();
