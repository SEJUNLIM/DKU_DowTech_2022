package com.example.dku_dow_dpp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyCouponFragment extends Fragment {
    Button buyCoupon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_coupon, container, false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Intent intentMyCouponDetail = new Intent(getActivity(),MyCouponDetailActivity.class);

        CollectionReference colref = db.collection("user/lim/coupon");
        colref.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                int i = 0;
                TableLayout tableLayout = view.findViewById(R.id.myCouponListTableLayout);
                TableRow newTableRow = new TableRow(getContext());
                for(QueryDocumentSnapshot document:task.getResult()){
                    Map<String, Object> couponData = document.getData();
                    View myCouponListView = LayoutInflater.from(getContext()).inflate(R.layout.my_coupon_list_layout,null);
                    Button theCoupon = myCouponListView.findViewById(R.id.myCoupon1);
                    theCoupon.setOnClickListener(view1 -> couponClick(intentMyCouponDetail, (HashMap<String,Object>) couponData));
                    String stringData = Objects.requireNonNull(couponData.get("brand")) +"\n"+ Objects.requireNonNull(couponData.get("name"));
                    theCoupon.setText(stringData);
                    if (i==0) {
                        TableRow tableRow = view.findViewById(R.id.tableRow);
                        tableRow.addView(myCouponListView);
                    } else if(i%2==1) {
                        newTableRow = new TableRow(getContext());
                        newTableRow.addView(myCouponListView);
                    } else {
                        newTableRow.addView(myCouponListView);
                        tableLayout.addView(newTableRow);
                    }
                    i++;
                    Log.d("@@@TAG@@@", document.getId()+"=>"+document.getData());
                }
                if (i!=0 && i%2==0) {
                    tableLayout.addView(newTableRow);
                }
            } else {
                Log.d("@@@TAG@@@", "Error getting documents: ", task.getException());
            }
        });

        buyCoupon = view.findViewById(R.id.buyCouponButton);
        buyCoupon.setOnClickListener(view1 -> {
            Intent intentBuyCoupon = new Intent(getContext(),BuyCouponActivity.class);
            startActivity(intentBuyCoupon);
        });

        return view;
    }

    protected void couponClick(Intent intent, Serializable docs){
        intent.putExtra("coupon", docs);
        startActivity(intent);
    }
//   document 넘겨주는 방안 생각하기
}