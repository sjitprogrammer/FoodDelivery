package com.aodev.fooddelivery.data;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aodev.fooddelivery.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder>{

    private ArrayList<PopularModel> items;
    int item_index = -1;
    public PopularAdapter(ArrayList<PopularModel> items){
        this.items = items;
    }
    @NonNull
    @Override
    public PopularAdapter.PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false);
        PopularAdapter.PopularViewHolder popularViewHolder = new PopularAdapter.PopularViewHolder(view);
        return popularViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.PopularViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PopularModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());
        holder.rate.setText(String. valueOf(currentItem.getRate()));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class PopularViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        TextView rate;
        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_popular);
            imageView = itemView.findViewById(R.id.img_popular);
            rate = itemView.findViewById(R.id.tv_popular_rate);
        }
    }
}
