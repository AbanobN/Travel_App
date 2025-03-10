package com.example.travel_app.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travel_app.databinding.ViewholderPopularBinding;
import com.example.travel_app.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewholder> {
    ArrayList<ItemModel> populars;
    Context context;
    ViewholderPopularBinding binding;

    public PopularAdapter(ArrayList<ItemModel> populars) {
        this.populars = populars;
    }

    @NonNull
    @Override
    public PopularAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        binding = ViewholderPopularBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.Viewholder holder, int position) {
        ItemModel popular = populars.get(position);
        binding.titleTxt.setText(popular.getTitle());
        binding.priceTxt.setText("$"+popular.getPrice()+"/Night");
        binding.addressTxt.setText(popular.getAddress());
        binding.scoreTxt.setText(""+popular.getScore());

        Glide.with(context)
                .load(popular.getPic().get(0))
                .into(binding.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return populars.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(ViewholderPopularBinding binding) {
            super(binding.getRoot());
        }
    }
}
