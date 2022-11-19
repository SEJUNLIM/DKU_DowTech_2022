package com.example.dku_dow_dpp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BabpickActivity extends AppCompatActivity {
    String food;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babpick);

        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        LinearLayout ll = (LinearLayout) findViewById(R.id.mainlayout);
        CollectionReference productRef = db.collection("user").document("lim").collection("coupon");

        productRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String restaurant_name = document.getData().get("brand").toString();
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view = inflater.inflate(R.layout.activity_babpickmain, null);
                        TextView name = view.findViewById(R.id.restaurant_name);

                        name.setText(restaurant_name);

                        Button findbtn = view.findViewById(R.id.find_btn);

                        findbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(BabpickActivity.this, BabpickselectActivity.class);

                                food = restaurant_name;

                                intent.putExtra("food", food);
                                startActivity(intent);
                            }
                        });

                        Button makebtn = view.findViewById(R.id.make_btn);

                        makebtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(BabpickActivity.this, BabpickmakingActivity.class);


                                food = name.getText().toString();

                                intent.putExtra("food", food);

                                startActivity(intent);
                            }
                        });

                        ll.addView(view);
                    }
                } else {
                    Log.d("TAG","", task.getException());
                }
            }
        });
    }
}