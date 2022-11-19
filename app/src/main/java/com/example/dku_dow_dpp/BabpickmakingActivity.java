package com.example.dku_dow_dpp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class BabpickmakingActivity extends AppCompatActivity {
    Button makingbtn;
    ImageButton backbtn;
    TimePicker mTimePicker;
    String name;
    static String current_id;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babpickmaking);

        CollectionReference productRef = db.collection("user");

        productRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        name = document.getData().get("nickname").toString();
                    }
                } else {
                    Log.d("TAG","", task.getException());
                }
            }
        });

        Intent getstr = getIntent();
        String food = getstr.getStringExtra("food");

        backbtn = findViewById(R.id.backbutton);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BabpickmakingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = new Intent(BabpickmakingActivity.this, Babpickresult2Activity.class);

        makingbtn = findViewById(R.id.making_btn);
        makingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hour = "";
                String min = "";
                mTimePicker = (TimePicker) findViewById(R.id.selecttime);
                if (android.os.Build.VERSION.SDK_INT >= 24) {
                    hour = Integer.toString(mTimePicker.getHour());
                    min = Integer.toString(mTimePicker.getMinute());
                }

                Map<String, Object> new_matching = new HashMap<>();
                new_matching.put("name", name);
                new_matching.put("hour", hour);
                new_matching.put("min", min);

                db.collection("babpick").document("식당별").collection(food)
                        .add(new_matching)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                current_id = documentReference.getId();
                                intent.putExtra("myroom_id", documentReference.getId());
                                Log.d(TAG, "DocumentSnapshot added with ID: ############" + current_id);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });

                intent.putExtra("food", food);
                intent.putExtra("hour", hour);
                intent.putExtra("min", min);

                startActivity(intent);
            }
        });
    }
}