package com.example.dku_dow_dpp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Objects;

public class ChargeListActivity extends AppCompatActivity {

    private Button point_charge, charge_list, page;
    private Button oneMonth, threeMonth, sixMonth, year;
    private ImageButton back_btn, my_btn;
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

        charge_list.setPaintFlags(charge_list.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
        page.setPaintFlags(page.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        oneMonth.setPaintFlags(oneMonth.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ref = db.collection("user").document("lim").collection("pointcharge");
        ref.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int i=0;
                TableLayout tableLayout = findViewById(R.id.chargeListTableLayout);
                TableRow newTableRow = new TableRow(getBaseContext());
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Map<String, Object> data = doc.getData();
                    View myPointChargeList = LayoutInflater.from(getBaseContext()).inflate(R.layout.charge_list_layout, null);
                    TextView tv_chargeDate = myPointChargeList.findViewById(R.id.tv_chargeDate);
                    TextView tv_chargePoint = myPointChargeList.findViewById(R.id.tv_chargePoint);

                    SimpleDateFormat sdt = new SimpleDateFormat("yyyy.MM.dd");
                    Timestamp chargeDateTS = (Timestamp) data.get("chargeDate");

                    String chargeDate = sdt.format(chargeDateTS.toDate());
                    String chargePoint = "₩".concat(Objects.requireNonNull(data.get("chargePoint")).toString());
                    tv_chargeDate.setText(chargeDate);
                    tv_chargePoint.setText(chargePoint);
                    if (i==0) {
                        newTableRow.addView(myPointChargeList);
                    } else if(i < 5) {
                        newTableRow = new TableRow(getBaseContext());
                        newTableRow.addView(myPointChargeList);
                    } else {
                        newTableRow.addView(myPointChargeList);
                        i=0;
                    }
                    tableLayout.addView(newTableRow);
                    i++;
                    Log.d("@@@TAG@@@", doc.getId() + "=>" + doc.getData());
                }
            } else {
                Log.d("@@@TAG@@@", "Error getting documents: ", task.getException());
            }
        });

        my_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChargeListActivity.this, MyPageActivity.class);
                startActivity(intent);
            }
        });

        back_btn.setOnClickListener(view -> onBackPressed());

        point_charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChargeListActivity.this, ChargePointActivity.class);
                startActivity(intent);
            }
        });

    }
}