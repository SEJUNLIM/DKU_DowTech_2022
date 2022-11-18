package com.example.dku_dow_dpp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class ChargeListActivity extends AppCompatActivity {

    private Button point_charge, charge_list, page;
    private Button oneMonth, threeMonth, sixMonth, year;
    private ImageButton back_btn, my_btn;
    private TextView charge_date, charge_money;
    private FirebaseFirestore db;

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
        charge_date = findViewById(R.id.charge_date);
        charge_money = findViewById(R.id.charge_money);

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

        db = FirebaseFirestore.getInstance();
        CollectionReference product = db.collection("user").document("lim").collection("pointcharge");
        product.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult()) {
                        charge_date.setText(doc.getData().get("chargeDate").toString());
                        charge_money.setText("₩".concat(doc.getData().get("chargePoint").toString()));
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        /*
        db = FirebaseFirestore.getInstance();
        DocumentReference productRef = db.collection("user").document("lim");
        productRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        */
    }
}
