package com.example.dku_dow_dpp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Babpickresult2Activity extends AppCompatActivity {
    ImageButton backbtn;
    Button returnbtn;
    TextView timetext;
    TextView nametext;
    String food;
    ImageView place;
    String eng_name;
    String current_id;

    public void onBackPressed() {
        super.onBackPressed();
        String myroom_id = BabpickmakingActivity.current_id;
        DocumentReference productRef = db.collection("babpick").document("식당별").collection(food).document(myroom_id);

        if(productRef != null) {
            productRef.delete();
        }

    }

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babpickresult2);
        Intent getstr = getIntent();


        eng_name = getstr.getStringExtra("eng_name");
        food = getstr.getStringExtra("food");

        String hour = getstr.getStringExtra("hour");
        String min = getstr.getStringExtra("min");
        current_id = getstr.getStringExtra("myroom_id");

        String time = hour + " 시   " + min +" 분";

        nametext = findViewById(R.id.nametext);
        nametext.setText(food);
        timetext = findViewById(R.id.timetext);
        timetext.setText(time);


        int iResId = getResources().getIdentifier( "@drawable/"+eng_name, "drawable", this.getPackageName() );
        place = findViewById(R.id.place);
        place.setImageResource(iResId);

        backbtn = findViewById(R.id.button);
        backbtn.setOnClickListener(view -> onBackPressed());

        returnbtn = findViewById(R.id.return_btn);
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Babpickresult2Activity.this, MainActivity.class);

                String myroom_id = BabpickmakingActivity.current_id;

                if(myroom_id != null) {
                    DocumentReference productRef = db.collection("babpick").document("식당별").collection(food).document(myroom_id);

                    if (productRef != null) {
                        productRef.delete();
                    }

                }

                startActivity(intent);
            }
        });


    }
}