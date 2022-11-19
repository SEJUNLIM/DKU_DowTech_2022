package com.example.dku_dow_dpp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class BabpickFragment extends Fragment {

    private View view;
    private Button firstbtn, firstMakeBtn, secondbtn, secondMakeBtn, thirdbtn, thirdMakeBtn;
    private EditText et_Text;
    private String food;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_babpick,container,false);

        firstbtn = view.findViewById(R.id.first_btn);
        firstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                startActivity(intent);
            }
        });

        firstMakeBtn = view.findViewById(R.id.first_btn2);
        firstMakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BabpickmakingActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);


                startActivity(intent);
            }
        });

        secondbtn = view.findViewById(R.id.second_btn);
        secondbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                startActivity(intent);
            }
        });

        secondMakeBtn = view.findViewById(R.id.second_btn2);
        secondMakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BabpickmakingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                et_Text = view.findViewById(R.id.secondFood);
                food = et_Text.getText().toString();

                intent.putExtra("food", food);

                startActivity(intent);
            }
        });

        thirdbtn = view.findViewById(R.id.third_btn);
        thirdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        thirdMakeBtn = view.findViewById(R.id.third_btn2);
        thirdMakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BabpickmakingActivity.class);

                et_Text = view.findViewById(R.id.thirdFood);
                food = et_Text.getText().toString();

                intent.putExtra("food", food);

                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}