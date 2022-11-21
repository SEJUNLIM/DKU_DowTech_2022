package com.example.dku_dow_dpp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
class Restaurant_inform {
    String ret_name;
    String eng_name;

    public Restaurant_inform(String ret_name, String eng_name) {
        this.ret_name = ret_name;
        this.eng_name = eng_name;
    }
}

public class BabpickFragment extends Fragment {
    private View view;
    private String food;
    private String eng_name;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayList<Restaurant_inform> arrayList = new ArrayList<>();
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
                        eng_name = document.getData().get("eng").toString();
                        String cl = document.getData().get("class").toString();

                        if(arrayList.contains(restaurant_name) || !cl.equals("restaurant"))
                            continue;

                        Restaurant_inform ri = new Restaurant_inform(restaurant_name, eng_name);
                        arrayList.add(ri);

                    }
                    Collections.sort(arrayList, (o1, o2) ->  o1.ret_name.compareTo(o2.ret_name));

                    for(Restaurant_inform restaurant_inform : arrayList) {
                        View viewinner = inflater.inflate(R.layout.activity_babpickmain, null);
                        TextView name = viewinner.findViewById(R.id.restaurant_name);

                        name.setText(restaurant_inform.ret_name);

                        Button findbtn = viewinner.findViewById(R.id.find_btn);

                        findbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(), BabpickselectActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                food = restaurant_inform.ret_name;
                                String english_name = restaurant_inform.eng_name;

                                intent.putExtra("eng_name", english_name);
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
                                String english_name = restaurant_inform.eng_name;

                                intent.putExtra("eng_name", english_name);
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