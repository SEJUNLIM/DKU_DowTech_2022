package com.example.dku_dow_dpp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BabpickFirstAdapter extends RecyclerView.Adapter<BabpickFirstAdapter.BabpickFirstHolder> {
    private ArrayList<haveCoupon> arrayList;
    private Context context;

    public BabpickFirstAdapter(ArrayList<haveCoupon> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public BabpickFirstHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_babpick, parent, false);
        BabpickFirstHolder holder = new BabpickFirstHolder(view);


        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BabpickFirstAdapter.BabpickFirstHolder holder, int position) {
        holder.rt_name.setText(arrayList.get(position).getBrand());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class BabpickFirstHolder extends RecyclerView.ViewHolder {
        EditText rt_name;

        public BabpickFirstHolder(@NonNull View itemView) {
            super(itemView);

            this.rt_name = itemView.findViewById(R.id.restaurant_name);
        }
    }
}
