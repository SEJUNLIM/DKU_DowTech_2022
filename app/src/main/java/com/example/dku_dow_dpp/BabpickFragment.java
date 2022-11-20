package com.example.dku_dow_dpp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class BabpickFragment extends Fragment {
    private View view;
    private String food;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayList<String> arrayList = new ArrayList<>();
        view = inflater.inflate(R.layout.fragment_babpick,container,false);

        try
        {
            Thread.sleep(250);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        CollectionReference productRef = db.collection("user").document("lim").collection("coupon");
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.mainlayout);
        productRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if(task.getResult().size() == 0) {
                        View if_non = inflater.inflate(R.layout.activity_ifnone, null);
                        TextView tv = if_non.findViewById(R.id.textview1);
                        tv.setText("정보가 존재하지 않습니다");
                        container.addView(if_non);
                    }
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String restaurant_name = document.getData().get("brand").toString();

                        if(arrayList.contains(restaurant_name))
                            continue;

                        arrayList.add(restaurant_name);

                    }
                    Collections.sort(arrayList);

                    for(String restaurant_name : arrayList) {
                        Log.d("TAG", restaurant_name);
                        View viewinner = inflater.inflate(R.layout.activity_babpickmain, null);
                        TextView name = viewinner.findViewById(R.id.restaurant_name);

                        name.setText(restaurant_name);

                        Button findbtn = viewinner.findViewById(R.id.find_btn);

                        findbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(), BabpickselectActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                food = restaurant_name;

                                intent.putExtra("food", food);
                                startActivity(intent);
                            }
                        });

                        Button makebtn = viewinner.findViewById(R.id.make_btn);

                        makebtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(), BabpickmakingActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                                food = name.getText().toString();

                                intent.putExtra("food", food);

                                startActivity(intent);
                            }
                        });

                        ll.addView(viewinner);
                    }
                } else {
                    Log.d("TAG","", task.getException());
                }
            }
        });



        return view;
    }
}